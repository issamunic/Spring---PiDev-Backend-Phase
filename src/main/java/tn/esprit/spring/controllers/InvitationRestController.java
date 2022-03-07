package tn.esprit.spring.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.mail.mailsender.ISimpleEmailExampleController;
import tn.esprit.spring.serviceInterface.*;
@RestController
@Api(tags = "Invitation Manager")
@RequestMapping("/Invitation")
public class InvitationRestController {

	@Autowired
	IInvitationService invitationService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html

	@Autowired
	IUserService IUserService;
	
	@Autowired
	ICodeInvitationCompanyService ICodeInvitationCompanyService;
	
	
	@ApiOperation(value = "getAllInvitations")
	@GetMapping("/getAll")
	@ResponseBody
	public List<Invitation> getAll() {
		return invitationService.getAll();
		
	}
	
	
	@ApiOperation(value = "addInvitation")
	@PostMapping("/add")
	@ResponseBody
	public Invitation add(@RequestBody Invitation invitation) {		
		return invitationService.add(invitation);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{invitation-id}")
	@ResponseBody
	public Invitation getById(@PathVariable("invitation-id") Long id) {
		return invitationService.getById(id);
	}
	
	@ApiOperation(value = "updateInvitation")
	@PutMapping("/update")
	@ResponseBody
	public Invitation update(@RequestBody Invitation invitation) {
		return invitationService.update(invitation);
	}
	
	
	@ApiOperation(value = "deleteProject")
	@DeleteMapping("/delete/{invitation-id}")
	@ResponseBody
	public void delete(@PathVariable("invitation-id") Long id) {
		invitationService.delete(id);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@ApiOperation(value = "SetinvitationAccepted")
	@PutMapping("/SetinvitationAccepted/{invitation-id}")
	@ResponseBody
	public Invitation SetinvitationAccepted(@PathVariable("invitation-id") Long id) {
		return invitationService.SetinvitationAccepted(id);
	}
	
	@ApiOperation(value = "getByCompany")
	@PostMapping("/getByCompany")
	@ResponseBody
	public List<Invitation> getByCompany(@RequestBody User Company) {
		return invitationService.getByCompany(Company);
		
	}	
	
	@ApiOperation(value = "getByEmailAndUser")
	@PutMapping("/getByEmailAndUser/{email}")
	@ResponseBody
	public Invitation SetinvitationAcceptedemail(@PathVariable("email") String email,@RequestBody User Company) {
		 return invitationService.getByEmailAndUser(email,Company);
	}
	
}
