package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Report;

public interface IReportService {
	
	public Report  addReclamation(Report r ) ; 
	public void deleteReclamation(int id);
	public void updateReclamation(Report r);
	public List<Report> RecalamationList(); 
	public List<Report>findReclamByUser(long  id);
	
		
	

}
