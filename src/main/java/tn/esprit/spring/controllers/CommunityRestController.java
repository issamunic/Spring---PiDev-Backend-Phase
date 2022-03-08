package tn.esprit.spring.controllers;


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
import tn.esprit.spring.entities.Community;
import tn.esprit.spring.serviceInterface.ICommunityService;

@RestController
@Api(tags = "Gestion des Communities")
@RequestMapping("/community")
public class CommunityRestController {
	
	@Autowired
	ICommunityService comService;
	
	

	
	@GetMapping("/retrieve-community/{community-id}")
	@ApiOperation(value = "Trouver un community" )
	@ResponseBody
	public Community retrieveClient(@PathVariable("community-id") Long communityId){
		return comService.retriveCommunity(communityId);
		
	}
	@PostMapping("/add-community")
	@ApiOperation(value = "Ajouter une community" )
	@ResponseBody
	public Community addClient(@RequestBody Community c){
		Community community = comService.addCommunity(c);
		return community;
	}
	@DeleteMapping("/remove-community/{community-id}")
	@ApiOperation(value = "Supprimer une community" )
	@ResponseBody
	public void removeClient(@PathVariable("community-id") Long communityId){
		comService.deleteCommunity(communityId);
	}
	@PutMapping("/modify-community")
	@ApiOperation(value = "Modifier un community" )
	@ResponseBody
	public Community modifyClient(@RequestBody Community community){
		return comService.updateCommunity(community);
	}

}
