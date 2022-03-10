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
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.ListEmployeePerCompany;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.serviceInterface.ICodeInvitationCompanyService;
import tn.esprit.spring.serviceInterface.IInvitationService;
import tn.esprit.spring.serviceInterface.IListEmployeePerCompany;
import tn.esprit.spring.serviceInterface.IUserService;

@RestController
@Api(tags = "ListEmployeePerCompany Manager")
@RequestMapping("/ListEmployeePerCompany")
public class ListEmployeePerCompanyController {

	@Autowired
	IListEmployeePerCompany IListEmployeePerCompany;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html

	@Autowired
	IUserService IUserService;
	
	
	@ApiOperation(value = "getAllListEmployeePerCompany")
	@GetMapping("/getAll")
	@ResponseBody
	public List<ListEmployeePerCompany> getAll() {
		return IListEmployeePerCompany.getAll();
		
	}
	
	
	@ApiOperation(value = "addListEmployeePerCompany")
	@PostMapping("/add")
	@ResponseBody
	public ListEmployeePerCompany add(@RequestBody ListEmployeePerCompany ListEmployeePerCompany) {		
		return IListEmployeePerCompany.add(ListEmployeePerCompany);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{id}")
	@ResponseBody
	public ListEmployeePerCompany getById(@PathVariable("id") Long id) {
		return IListEmployeePerCompany.getById(id);
	}
	
	@ApiOperation(value = "update")
	@PutMapping("/update")
	@ResponseBody
	public ListEmployeePerCompany update(@RequestBody ListEmployeePerCompany ListEmployeePerCompany) {
		return IListEmployeePerCompany.update(ListEmployeePerCompany);
	}
	
	
	@ApiOperation(value = "delete")
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		IListEmployeePerCompany.delete(id);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@ApiOperation(value = "getCompany")
	@PostMapping("/getCompany/{id}")
	@ResponseBody
	public User getComany(@PathVariable("id") Long id) {
		return IListEmployeePerCompany.getComany(id);
		
	}	
	
	@ApiOperation(value = "getEmployees")
	@PostMapping("/getEmployees/{id}")
	@ResponseBody
	public List<User> getEmployees(@PathVariable("id") Long id) {
		 return IListEmployeePerCompany.getEmployees(id);
	}
	
}
