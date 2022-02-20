package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.serviceInterface.IInvitationService;
@RestController
@Api(tags = "Invitation Manager")
@RequestMapping("/Invitation")
public class InvitationRestController {

	@Autowired
	IInvitationService invitationService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
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
		System.out.println("********************"+id+"**********");
		return invitationService.getById(id);
	}
	
	@ApiOperation(value = "updateInvitation")
	@PutMapping("/update")
	@ResponseBody
	public Invitation update(@RequestBody Invitation invitation) {
		return invitationService.update(invitation);
	}
	
	
	@ApiOperation(value = "deleteProject")
	@DeleteMapping("/delete/{project-id}")
	@ResponseBody
	public void delete(@PathVariable("project-id") Long id) {
		invitationService.delete(id);
	}
}
