package tn.esprit.spring.controllers;

import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.SubscriptionCompany;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.serviceInterface.IHistorySubcriptionCompanyService;
import tn.esprit.spring.serviceInterface.ISubscriptionCompanyService;

@RestController
@Api(tags = "SubscriptionCompany Manager")
@RequestMapping("/SubscriptionCompany")
public class SubscriptionCompanyRestController {

	@Autowired
	ISubscriptionCompanyService SubscriptionCompanyService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	@Autowired
	IHistorySubcriptionCompanyService HistorySubcriptionCompanyService;
	
	@ApiOperation(value = "getAllSubscriptionCompanys")
	@GetMapping("/getAll")
	@ResponseBody
	public List<SubscriptionCompany> getAll() {
		return SubscriptionCompanyService.getAll();
		
	}
	
	
	@ApiOperation(value = "addSubscriptionCompany")
	@PostMapping("/add")
	@ResponseBody
	public SubscriptionCompany add(@RequestBody SubscriptionCompany SubscriptionCompany) {
	
		return SubscriptionCompanyService.add(SubscriptionCompany);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{SubscriptionCompany-id}")
	@ResponseBody
	public SubscriptionCompany getById(@PathVariable("SubscriptionCompany-id") Long id) {
		System.out.println("********************"+id+"**********");
		return SubscriptionCompanyService.getById(id);
	}
	
	@ApiOperation(value = "updateSubscriptionCompany")
	@PutMapping("/update")
	@ResponseBody
	public SubscriptionCompany update(@RequestBody SubscriptionCompany SubscriptionCompany) {
		return SubscriptionCompanyService.update(SubscriptionCompany);
	}
	
	
	@ApiOperation(value = "deleteProject")
	@DeleteMapping("/delete/{SubscriptionCompany-id}")
	@ResponseBody
	public void delete(@PathVariable("SubscriptionCompany-id") Long id) {
		SubscriptionCompanyService.delete(id);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@ApiOperation(value = "UpgradeSubscriptionCompany")
	@PutMapping("/UpgradeSubscriptionCompany")
	@ResponseBody
	public String UpgradeSubscriptionCompany(@RequestBody SubscriptionCompany SubscriptionCompany) {
		 SubscriptionCompanyService.UpgradeSubscriptionCompany(SubscriptionCompany);
		 if(SubscriptionCompany.getNbrEmployeeMax()<50) {
			 return "You have"+SubscriptionCompany.getNbrEmployeeMax()*0.2;
		 }
		 if(SubscriptionCompany.getNbrEmployeeMax()>50 && SubscriptionCompany.getNbrEmployeeMax()<100) {
			 return"You have"+SubscriptionCompany.getNbrEmployeeMax()*0.1;
		 }
		 if(SubscriptionCompany.getNbrEmployeeMax()>100) {
			 return"You have"+SubscriptionCompany.getNbrEmployeeMax()*0.05;
		 }
		 return null;
	}
	
	@ApiOperation(value = "InitilainitializationOfSubscriptionCompany")
	@PostMapping("/InitilainitializationOfSubscriptionCompany")
	@ResponseBody
	public SubscriptionCompany InitilainitializationOfSubscriptionCompany(@RequestBody User Company) {
	
		return SubscriptionCompanyService.InitilainitializationOfSubscriptionCompany(Company);
	}
	
	@ApiOperation(value = "getByUser")
	@GetMapping("/getByUser")
	@ResponseBody
	public SubscriptionCompany getByUser(@RequestBody User Company) {
		return SubscriptionCompanyService.getByUser(Company);
	}
}
