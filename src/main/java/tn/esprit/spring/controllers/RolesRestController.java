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
import tn.esprit.spring.entities.Roles;
import tn.esprit.spring.serviceInterface.IRolesService;

@RestController
@Api(tags = "roles management")
@RequestMapping("/roles")
public class RolesRestController {
	
	@Autowired
	IRolesService rolesService;
	
	@ApiOperation(value = "get list roles")
	@GetMapping("/retrieve-all-roles")
	@ResponseBody
	public List<Roles> getRoless() {
		return rolesService.retrieveAllRoles();
	}
	
	@ApiOperation(value = "retrieve roles")
	@GetMapping("/retrieve-roles/{rolesId}")
	@ResponseBody
	public Roles getRoles(@PathVariable("rolesId") Long id) {
		return rolesService.retrieveRoles(id);
	}
	
	@ApiOperation(value = "add roles")
	@PostMapping("/add-roles")
	@ResponseBody
	public Roles addRoles(@RequestBody Roles roles){
		return rolesService.addRoles(roles);
	}
	
	@ApiOperation(value = "delete roles")
	@DeleteMapping("/remove-roles/{roles-id}")
	@ResponseBody
	public void deleteRoles(@PathVariable("roles-id") Long idRoles){
		rolesService.deleteRoles(idRoles);
	}
	
	@ApiOperation(value = "update roles")
	@PutMapping("/modify-roles")
	@ResponseBody
	public Roles updateRoles(@RequestBody Roles roles){
		return rolesService.updateRoles(roles);
	}
	
	
	
}
