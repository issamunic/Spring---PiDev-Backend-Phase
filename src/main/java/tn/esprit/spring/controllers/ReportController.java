package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.serviceInterface.IReportService;

@RestController
@Api(tags = "Reports managment")
@RequestMapping("/Report")
public class ReportController {
	
	@Autowired
	IReportService ReportService;
	
	@PostMapping("/add-Report")
	public Report addReport(@RequestBody Report r)
	{
		return ReportService.addReclamation(r);
	}
	
	@GetMapping("/get-Reclamation")
	public List<Report> getReclam()
	{
		return ReportService.RecalamationList();
	}
	
	
	@DeleteMapping("/delete-Reclamation/{reclam-id}")
	public void deleteReclamation(@PathVariable("reclam-id") int id)
	{
		 ReportService.deleteReclamation(id);
	}
	
	@PostMapping("/update-Report")
	public void UpdateReport(@RequestBody Report r)
	{
		  ReportService.updateReclamation(r);
	}
	
	
	@GetMapping("/ReclamationByUserId/{user-id}")
	public List<Report> ListReportByIdUser(@PathVariable("user-id") int id)
	{
		return ReportService.findReclamByUser(id);
	}
	
	@GetMapping("/Reclamationtest")
	public Map<String, Integer> NombreDesReclamParSemaine()
	{
		 return ReportService.NombreDesReclamParSemaine();
	}
	
	@GetMapping("/Reclamationtest2")
	public Map<String, Integer> NombreDesReclamParmois()
	{
		 return ReportService.NombreDesReclamParMois();
	}
	
	@GetMapping("/Reclamationtest3")
	public Map<String, Integer> NombreDesReclamParAn()
	{
		 return ReportService.NombreDesReclamParAn();
	}
	
	
	

}