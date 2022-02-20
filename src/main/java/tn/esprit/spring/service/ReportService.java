package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.RepportRepository;
import tn.esprit.spring.repository.UserRepo;
import tn.esprit.spring.serviceInterface.IReportService;

@Service
@Slf4j
public class ReportService implements IReportService {
	
	@Autowired
	 RepportRepository repoRecalam;
	
	@Autowired
	 UserRepo userRepo;

	
	
	
	@Override
	public Report addReclamation(Report r) {
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		/*cal.setTime(date);
		cal.add(Calendar.DATE, -8);
		Date date1 = cal.getTime();
		*/
		r.setDateReport(date);
		r.setUtilisateur(userRepo.findById((long) 2).orElse(null));
		repoRecalam.save(r);
		
		
		return r;
		
	}
	
	@Override
	public Map<String, Integer> NombreDesReclamParSemaine() {
		 Map<String, Integer> mapReclamweek = new HashMap<String, Integer>();
		 Date date = new Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			cal.add(Calendar.DATE, -7);

			Date date1 = cal.getTime();
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println(formater.format(date)+"**********"+formater.format(date1));

		 
		 List<Report> listReport = (List<Report>) repoRecalam.ListReportOfweekAgo(date1,date);
		 for(Report report : listReport) {
			 System.out.println(report.getDateReport());
			 
			 //System.out.println(report.getDateReport());
			 if(!mapReclamweek.containsKey(formater.format(report.getDateReport()))) {
				 mapReclamweek.put(formater.format(report.getDateReport()), 0);
				 
			 }
			 if(mapReclamweek.containsKey(formater.format(report.getDateReport()))) {
				 
				 mapReclamweek.put(formater.format(report.getDateReport()),mapReclamweek.get(formater.format(report.getDateReport()))+1 );
				 
			 }
			 
			}
		 


			return mapReclamweek;
			
		 
		 
	}
	
	@Override
	public  Map<String, Integer> NombreDesReclamParMois() {
		
		 Map<String, Integer> mapReclamweek = new HashMap<String, Integer>();
		 
		 
		 
		 Date date = new Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			
			for(int i=1;i<5;i++) {
			cal.setTime(date);
			cal.add(Calendar.DATE, -7*i);

			Date date1 = cal.getTime();
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println(formater.format(date)+"**********"+formater.format(date1));
			 List<Report> listReport = (List<Report>) repoRecalam.ListReportOfweekAgo(date1,date);
			 mapReclamweek.put("Week "+i , listReport.size());
			 date = date1;
			}
			 

			 
			return mapReclamweek;

		
	}
	
	@Override
	public  Map<String, Integer> NombreDesReclamParAn() {
		

		
		 Map<String, Integer> mapReclamweek = new HashMap<String, Integer>();
		 
		 
		 
		 Date date = new Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			
			for(int i=1;i<13;i++) {
			cal.setTime(date);
			cal.add(Calendar.DATE, -30*i);

			Date date1 = cal.getTime();
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println(formater.format(date)+"**********"+formater.format(date1));
			 List<Report> listReport = (List<Report>) repoRecalam.ListReportOfweekAgo(date1,date);
			 mapReclamweek.put("month"+i, listReport.size());
			 date = date1;
			}
			 

			 
			return mapReclamweek;

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void deleteReclamation(int id) {
		repoRecalam.deleteById(id);

	}

	@Override
	public void updateReclamation(Report r) {
		
		repoRecalam.save(r);
		
	}

	@Override
	public List<Report> RecalamationList() {
		List<Report> listReclam = (List<Report>) repoRecalam.findAll();
		return  listReclam;
	}

	@Override
	public List<Report> findReclamByUser(long id) {
		User user = userRepo.findById(id).orElse(null);
		return user.getReclamations();
	}
	
	
	
	
	
	
	
	

}
