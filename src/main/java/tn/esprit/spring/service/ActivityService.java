package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Activity;
import tn.esprit.spring.entities.Progress;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.TripPlan;
import tn.esprit.spring.repository.ActivityRepo;
import tn.esprit.spring.repository.TripPlanRepo;
import tn.esprit.spring.serviceInterface.IActivityService;

@Service
@Slf4j
public class ActivityService implements IActivityService {

	@Autowired
	ActivityRepo ActivityRepo;
	
	@Autowired
	TripPlanRepo tripPlanRepo;

	@Override
	public List<Activity> getAll() {
		try {
			List<Activity> Activitys = (List<Activity>) ActivityRepo.findAll();
			for (Activity Activity : Activitys) {
				log.info(" Activity : " + Activity);
			}
			return Activitys;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String add(Activity activity, int idTripPlan) {
		
		TripPlan tp = tripPlanRepo.findById((long) idTripPlan).orElse(null);
		
		
		
		
		List<Activity> Activitys = (List<Activity>) ActivityRepo.findAll();
		int cpt = 0;
		

		if(Activitys.size()!=0 ) {
			System.out.println("dkhal lel if lowlaa");

			for(Activity a  : Activitys) {
				System.out.println(	"dkhal lel boucle for thenya ");
				//ActivityRepo.save(activity);
				
				System.out.println("linjib fiha : "+a.getEndActivity()+"li n7ot fiha : "+activity.getEndActivity());
				
				if(a.getStartActivity()!=null && a.getEndActivity()!=null) {
				if ((activity.getStartActivity().equals(a.getStartActivity()) || activity.getStartActivity().after(a.getStartActivity())) && (activity.getEndActivity().equals(a.getEndActivity()) || activity.getStartActivity().before(a.getEndActivity()))
						|| (a.getStartActivity().equals(activity.getStartActivity())&& a.getEndActivity().equals(activity.getEndActivity()))
						) {
						cpt = cpt + 1;
				}
				}
				/*else {
					System.out.println(	"dkhal lel else");
					//ActivityRepo.save(activity);
					//return " valid";
					
					}*/
			}
			System.out.println(	"cpt :"+cpt);
			
			if(cpt==0 && activity.getStartActivity().before(tp.getTrip().getEndDate())) {
				activity.setTripPlan(tp);
				ActivityRepo.save(activity);
				

				return "Activity is Valid";	
				}
			
			if(activity.getStartActivity().after(tp.getTrip().getEndDate())) {
				return "check the dates of your activity because they are outside the travel period !";
			}
			
				else{
				return " Date Reserved! , Look for another date or contact the planner";
				}
		}
		return "";
		//ActivityRepo.save(activity);
		//return "Activité Valid";
			
			
		
	}
	
	

	@SuppressWarnings("deprecation")
	@Override
	public void update(long idTripPlan) {
		TripPlan p =tripPlanRepo.findById(idTripPlan).orElse(null); // 2 bech twali dynamique 
		List<Activity> Activitys = (List<Activity>) ActivityRepo.ListActivityWithoutDate(p);
		Date dateDebut = p.getTrip().getStartDate();
		   Calendar calendar1 = Calendar.getInstance();
		   calendar1.setTime(dateDebut);
		   calendar1.add(Calendar.DATE, 1);
		Date dateFin = p.getTrip().getEndDate();
		System.out.println("dd:"+dateDebut+"df:"+dateFin);
		System.out.println("size : "+ Activitys.size());
		Date debut = calendar1.getTime();
		debut.setHours(8);
		debut.setMinutes(0);
		debut.setSeconds(0);
		calendar1.setTime(debut);
		Date faza = calendar1.getTime();
		Calendar calendar2 = Calendar.getInstance();   
		   calendar2.setTime(debut); 
		   
		for(Activity a  : Activitys) {	
			if(a.getStartActivity()==null && a.getEndActivity()==null ) {
				   if(calendar2.getTime().getHours()+a.getPeriod()<18) {
					   a.setStartActivity( calendar2.getTime());
			  
				   calendar2.add(Calendar.HOUR, a.getPeriod());
				   
				   a.setEndActivity(calendar2.getTime());
				   System.out.println("d5al lel if lowla");
				   }  
				   else if(calendar2.getTime().getHours()+a.getPeriod()>=18) {
			
						calendar2.add(Calendar.DATE,1);
						faza= calendar2.getTime();
						faza.setHours(8);
						faza.setMinutes(0);
						faza.setSeconds(0);
						calendar2.setTime(faza);
						
						System.out.println("calander****:"+calendar2);
						
						//calendar2.setTime(debut);
						   a.setStartActivity( calendar2.getTime());
						
				    calendar2.add(Calendar.HOUR, a.getPeriod());
				    a.setEndActivity(calendar2.getTime());
				    System.out.println("d5al lel if thenya");
				   }  
				   System.out.println("datedebut"+a.getPeriod());
				   System.out.println("datefin"+a.getPeriod());
				   
				   ActivityRepo.save(a);
				   
				   debut = calendar2.getTime();
			
				
			}
			
			
		}


	}

	//@Scheduled(fixedRate = 600000)
	public void delete() {
		List<Activity> Activitys = (List<Activity>) ActivityRepo.ListActivityInfCurentDate();
		
		for (Activity Activity : Activitys) {
			if(Activity.getProgress().equals(Progress.done)) {
			ActivityRepo.delete(Activity);
			}
			
		}
		
		System.out.println("deleted");

		

	}
	
	
	public List<Activity> addActivityByResponsable(List<Activity> activities, int id) {
		
		TripPlan trp = tripPlanRepo.findById((long)id).orElse(null);
		for(Activity a : activities ) {
		a.setTripPlan(trp);
		ActivityRepo.save(a);
		}
		return activities;
		 
	}

	

	@Override
	public Activity getById(Long Activityid) {
		
		
		try {
			Activity Activity = ActivityRepo.findById(Activityid).orElse(null);
			return Activity;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void addActivityToTripPlan(long idTripPlan, long idActivity) {
		TripPlan p =  tripPlanRepo.findById(idTripPlan).orElse(null);
		Activity a =  ActivityRepo.findById(idActivity).orElse(null);
		a.setTripPlan(p);
		ActivityRepo.save(a);

		
		
	}

	@Override
	public List<Activity> listActivityByTripPlanId(long id) {
		TripPlan p =  tripPlanRepo.findById(id).orElse(null);
		List<Activity> Activities = (List<Activity>) ActivityRepo.ListActivityByIdTripplan(p);
		return Activities;

		
	}

	@Override
	public String makeDoneActivity(long id) {
		Activity activity = ActivityRepo.findById(id).orElse(null);
		if((activity.getProgress()!=Progress.notdone) || (activity.getProgress()==Progress.done)) {
		activity.setProgress(Progress.done);
		ActivityRepo.save(activity);
		
		return "Good Job, your Activity is Done";
		}

		
		else {
			return "you can't make it done because you missed the deadline You can consult your responsible";

		}
		

		
		

	}

	
	//@Scheduled(fixedRate = 6000)
	@Override
	public void makeNotDoneActivity() {
		List<Activity> activities = (List<Activity>) ActivityRepo.findAll();
		 Date date = new Date(System.currentTimeMillis());
		 for (Activity activity : activities) {
			 
			 if((activity.getProgress()!=Progress.done)&&(activity.getEndActivity().before(date))) {
				 activity.setProgress(Progress.notdone);
				 ActivityRepo.save(activity);
				 System.out.println("***************traitement***************");
				 
			 }
				
				
			}
	
	}
	
	
	
	

}