package tn.esprit.spring.serviceInterface;

import java.util.List;
import java.util.Map;

import tn.esprit.spring.entities.Report;

public interface IReportService {
	
	public Report  addReclamation(Report r ) ; 
	public void deleteReclamation(int id);
	public void updateReclamation(Report r);
	public List<Report> RecalamationList(); 
	public List<Report>findReclamByUser(long  id);

	public  Map<String, Integer> NombreDesReclamParMois();
    public Map<String, Integer> NombreDesReclamParSemaine();
	public Map<String, Integer> NombreDesReclamParAn();
	public Map<String, Integer> NombreDesReclamParType(String periode);
	public int NombreDesReclamParDay();
	public int NombreDesReclamTraite();
	public int NombreDesReclamNonTraite();
	
		
	

}
