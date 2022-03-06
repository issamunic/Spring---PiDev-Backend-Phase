package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.ReclamStatus;
import tn.esprit.spring.entities.ReclamType;
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
		r.setStaus(ReclamStatus.Inprogress);
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
			 if(mapReclamweek.containsKey(formater.format(report.getDateReport()))==false) {
				 mapReclamweek.put(formater.format(report.getDateReport()), 0);
				 
			 }
			  if(mapReclamweek.containsKey(formater.format(report.getDateReport()))) {
				 
				 mapReclamweek.put(formater.format(report.getDateReport()),mapReclamweek.get(formater.format(report.getDateReport()))+1 );
				 
			 }
			 
			 
			 
			}
		 
			return mapReclamweek;
			
	}
	
	
	/*
	 @Override
	public  Map<String, Integer> NombreDesReclamParMois() {
		
		 Map<String, Integer> mapReclamweek = new HashMap<String, Integer>();
		 
		 
		 
		 Date dateFin = new Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFin);
			for(int i=1;i<5;i++) {
			
			cal.add(Calendar.DATE, -7*i);

			Date date1 = cal.getTime();
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println(formater.format(dateFin)+"**********"+formater.format(date1));
			 List<Report> listReport = (List<Report>) repoRecalam.ListReportOfweekAgo(date1,date);
			 mapReclamweek.put(cal.getTime().toString() , listReport.size());
			 dateFin = date1;
			}    
			 

			 
			return mapReclamweek;

		
	}
	 */
	
	@Override
	public  Map<String, Integer> NombreDesReclamParMois() {
		
		 Map<String, Integer> mapReclamweek = new HashMap<String, Integer>();
		 
		 
		 
		 Date dateFin = new Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFin);
			for(int i=1;i<5;i++) {
			
			cal.add(Calendar.DATE, -7);

			Date date1 = cal.getTime();
			
			
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println(formater.format(dateFin)+"**********"+formater.format(date1));
			 List<Report> listReport = (List<Report>) repoRecalam.ListReportOfweekAgo(date1,dateFin);
			 mapReclamweek.put(cal.getTime().toString() , listReport.size());
			 dateFin = date1;
			 
			 System.out.println("current date : "+dateFin);
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
			
			
			cal.add(Calendar.DATE, -30);

			Date date1 = cal.getTime();
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println(formater.format(date)+"**********"+formater.format(date1));
			 List<Report> listReport = (List<Report>) repoRecalam.ListReportOfweekAgo(date1,date);
			 mapReclamweek.put(cal.getTime().toString(), listReport.size());
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
		
		
		r.setStaus(ReclamStatus.Handled);
		
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

	@Override
	public Map<String, Integer> NombreDesReclamParType(String periode) {
		
		 Map<String, Integer> mapReclamweek = new HashMap<String, Integer>();
		 Date date = new Date(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			if(periode.equals("semaine")) {
			cal.add(Calendar.DATE, -7);
			}
			if(periode.equals("mois")) {
				cal.add(Calendar.DATE, -30);
			}
			if(periode.equals("an")) {
				cal.add(Calendar.DATE, -360);
			}

			Date date1 = cal.getTime();
			
			SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println(formater.format(date)+"**********"+formater.format(date1));

		 
		 List<Report> listReport = (List<Report>) repoRecalam.ListReportOfweekAgo(date1,date);
		 
		 int a = 0;
		 int b =0;
		 int c =0;
		 int d = 0;
		 int e =0;
		 int f = 0; 
		 int g =0;
		 int h =0;
		 for(Report report : listReport) {

			 if(report.getType().equals(ReclamType.A)){
				 a = a + 1;
				 mapReclamweek.put("A", a);
			 }
			 
			 
			 if(report.getType().equals(ReclamType.B)){
				 b = b + 1;
				 mapReclamweek.put("B", b);
			 }
			 if(report.getType().equals(ReclamType.C)){
				 c = c + 1;
				 mapReclamweek.put("C", c);
			 }
			 if(report.getType().equals(ReclamType.D)){
				 d = d + 1;
				 mapReclamweek.put("D", d);
			 }
			 if(report.getType().equals(ReclamType.E)){
				 e = e + 1;
				 mapReclamweek.put("E", e);
			 }
			 if(report.getType().equals(ReclamType.F)){
				 f = f + 1;
				 mapReclamweek.put("F", f);
			 }
			 if(report.getType().equals(ReclamType.G)){
				 g = g + 1;
				 mapReclamweek.put("G", g);
			 }
			 if(report.getType().equals(ReclamType.H)){
				 h = h + 1;
				 mapReclamweek.put("H", h);
			 }
			 
			 
			 
			}
		 
			return mapReclamweek;
			
	}

	@Override
	public int NombreDesReclamParDay() {
		
		 Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			

		 
	        LocalDate date2 = LocalDate.parse(formater.format(date), DateTimeFormatter.ISO_DATE);

	        System.out.println(date2.toString());
	        
return 0;				 



			}
			


		


	@Override
	public int NombreDesReclamTraite() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int NombreDesReclamNonTraite() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	

}
