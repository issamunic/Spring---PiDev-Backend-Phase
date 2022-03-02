package tn.esprit.spring.controllers;

import java.util.Date;
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
import tn.esprit.spring.entities.HistorySubcriptionCompany;
import tn.esprit.spring.serviceInterface.IHistorySubcriptionCompanyService;

@RestController
@Api(tags = "HistorySubcriptionCompany Manager")
@RequestMapping("/HistorySubcriptionCompany")
public class HistorySubcriptionCompanyRestController {

	
	@Autowired
	IHistorySubcriptionCompanyService HistorySubcriptionCompanyService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
	
	@ApiOperation(value = "getAllHistorySubcriptionCompanys")
	@GetMapping("/getAll")
	@ResponseBody
	public List<HistorySubcriptionCompany> getAll() {
		return HistorySubcriptionCompanyService.getAll();
		
	}
	
	
	@ApiOperation(value = "addHistorySubcriptionCompany")
	@PostMapping("/add")
	@ResponseBody
	public HistorySubcriptionCompany add(@RequestBody HistorySubcriptionCompany HistorySubcriptionCompany) {
		
		HistorySubcriptionCompany.setDateHistorySubcriptionCompany(new Date());
		return HistorySubcriptionCompanyService.add(HistorySubcriptionCompany);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{HistorySubcriptionCompany-id}")
	@ResponseBody
	public HistorySubcriptionCompany getById(@PathVariable("HistorySubcriptionCompany-id") Long id) {
		System.out.println("********************"+id+"**********");
		return HistorySubcriptionCompanyService.getById(id);
	}
	
	@ApiOperation(value = "updateHistorySubcriptionCompany")
	@PutMapping("/update")
	@ResponseBody
	public HistorySubcriptionCompany update(@RequestBody HistorySubcriptionCompany HistorySubcriptionCompany) {
		return HistorySubcriptionCompanyService.update(HistorySubcriptionCompany);
	}
	
	
	@ApiOperation(value = "deleteProject")
	@DeleteMapping("/delete/{HistorySubcriptionCompany-id}")
	@ResponseBody
	public void delete(@PathVariable("HistorySubcriptionCompany-id") Long id) {
		HistorySubcriptionCompanyService.delete(id);
	}
}
