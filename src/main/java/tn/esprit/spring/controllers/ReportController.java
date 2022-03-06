package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Activity;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepo;
import tn.esprit.spring.serviceInterface.IReportService;

@RestController
@Api(tags = "Reports managment")
@RequestMapping("/Report")
public class ReportController {
	
	
	
	@Autowired
	IReportService ReportService;
	
	@Autowired
	UserRepo userRepo;
	
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
	
	@GetMapping("/semaine")
	public Map<String, Integer> NombreDesReclamParSemaine()
	{
		 return ReportService.NombreDesReclamParSemaine();
	}
	
	@GetMapping("/mois")
	public Map<String, Integer> NombreDesReclamParmois()
	{
		 return ReportService.NombreDesReclamParMois();
	}
	
	@GetMapping("/an")
	public Map<String, Integer> NombreDesReclamParAn()
	{
		 return ReportService.NombreDesReclamParAn();
	}
	
	@GetMapping("/chartReportType/{periode}")
	public Map<String, Integer> NombreDesReclamParType(@PathVariable("periode") String periode){
		return ReportService.NombreDesReclamParType(periode);
	}
	
	@GetMapping("/get-reclamParday")
	public int NombreDesReclamParDay() {
		return ReportService.NombreDesReclamParDay();
		
	}
	
	@ApiOperation(value = "updateReport")
	@PutMapping("/update")
	@ResponseBody
	public void update(@RequestBody Report r) {
	
		  String ACCOUNT_SID = "AC39b3348ddc32e1b8ea10dab06afc1f9a";
	        String AUTH_TOKEN = "45762239195021d61a158860b28a1eba";

	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("whatsapp:+21652135404"),
	                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
	                r.getResponse()+"qui est lié au"+r.getType()+r.getUtilisateur().getFirstNameEmploye())
	                .create();

	        System.out.println(message.getSid());
	}
	
	
	@GetMapping("/msg")
    public void sendMSG() {
        String ACCOUNT_SID = "AC39b3348ddc32e1b8ea10dab06afc1f9a";
        String AUTH_TOKEN = "45762239195021d61a158860b28a1eba";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21652135404"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Hello")
                .create();

        System.out.println(message.getSid());

    }
	
	
	
	
	

}
