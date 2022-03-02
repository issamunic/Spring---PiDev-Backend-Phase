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
	public String add(Activity activity) {
		List<Activity> Activitys = (List<Activity>) ActivityRepo.findAll();
		int cpt = 0;
		

		if(Activitys.size()!=0 ) {
			System.out.println("dkhal lel if lowlaa");

			for(Activity a  : Activitys) {
				System.out.println(	"dkhal lel boucle for thenya ");
				//ActivityRepo.save(activity);
				
				if(a.getStartActivity()!=null && a.getEndActivity()!=null) {
				if(((activity.getStartActivity().equals(a.getStartActivity()))||((activity.getStartActivity().after(a.getStartActivity()))))&&((activity.getEndActivity().equals(a.getEndActivity()))||((activity.getEndActivity().before(a.getEndActivity()))))) {
				//if(activity.getStartActivity().after(a.getStartActivity()&&activity.getEndActivity().before(a.getEndActivity())) {
					//if((activity.getStartActivity().after(a.getStartActivity())) && (activity.getEndActivity().before(a.getEndActivity()))){
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
			
			if((cpt==0)&&(activity.getStartActivity().before(activity.getEndActivity()))) {
				
				ActivityRepo.save(activity);
				return "Activité Valid";	
				}
				
				else{
				return "Date Réservé ! , Cherchez une autre date ou contacté le planificateur";
				}

			
		}
		
		ActivityRepo.save(activity);
		return "Activité Valid";
			
			
		
	}
	
	

	@SuppressWarnings("deprecation")
	@Override
	public void update() {
		TripPlan p =tripPlanRepo.findById((long) 2).orElse(null); // 2 bech twali dynamique 
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

		for(Activity a  : Activitys) {
			if(a.getStartActivity()==null && a.getEndActivity()==null) {
				Calendar calendar2 = Calendar.getInstance();
				
				   calendar2.setTime(debut);
				   if(debut.getHours()+a.getPeriod()<18) {
				   calendar2.add(Calendar.HOUR, a.getPeriod());
				   }
				   
				   if(debut.getHours()+a.getPeriod()>=18) {
				   calendar2.add(Calendar.DATE, 1);
					debut.setHours(8);
					debut.setMinutes(0);
					debut.setSeconds(0);
				   calendar2.add(Calendar.HOUR, a.getPeriod());
				   }
				   
				   
				   System.out.println("datedebut"+a.getPeriod());
				   System.out.println("datefin"+a.getPeriod());
				   a.setStartActivity( debut);
				   a.setEndActivity(calendar2.getTime());
				   ActivityRepo.save(a);
					
					
				
				
				
				debut = calendar2.getTime();
				
			}
			
			
		}


	}

	@Scheduled(fixedRate = 600000)
	public void delete() {
		List<Activity> Activitys = (List<Activity>) ActivityRepo.ListActivityInfCurentDate();
		for (Activity Activity : Activitys) {
			ActivityRepo.delete(Activity);
			
		}
		
		System.out.println("deleted");

		

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
	
	

}