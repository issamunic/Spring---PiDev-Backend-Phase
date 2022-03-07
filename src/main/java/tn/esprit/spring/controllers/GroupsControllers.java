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

import tn.esprit.spring.entities.GroupeType;
import tn.esprit.spring.entities.Groups;
import tn.esprit.spring.entities.themes;
import tn.esprit.spring.serviceInterface.IGroupsService;

@RestController
@RequestMapping("/group")
public class GroupsControllers{
	@Autowired
	IGroupsService groupService;
	
	//no
	@PostMapping("/CreateGroups/{idUser}")
	@ResponseBody
	public void CreateGroup(@RequestBody Groups g ,@PathVariable("idUser") List<Long> idUser) {
		groupService.CreateGroup(g,idUser);
	}
	
	// okey
	@DeleteMapping("/deleteGroups/{idGroup}")
		public void deleteGroup(@PathVariable("idGroup")Long idGroup) {
			groupService.deleteGroup(idGroup);
		}
		
		
		
	// okey
	@PutMapping("/upadateEtatGroup/{idGroup}/{etatGroup}/{dureeExpiration}")
	public void UpadateEtatGroup(@PathVariable("idGroup") Long idGroup,@PathVariable("etatGroup") GroupeType etatGroup,@PathVariable("dureeExpiration") Long dureeExpiration) {
		groupService.UpadateEtatGroup(idGroup, etatGroup,dureeExpiration);
	}
	
	// okey
	@PutMapping("/updateTheme/{idGroup}/{theme}")
	public void ChangeThemeGroup(@PathVariable("idGroup")Long idGroup,@PathVariable("theme") themes theme) {
		groupService.ChangeThemeGroup(idGroup, theme);
	}
	// okey
	@PutMapping("/renameGroups/{idGroup}/{Newname}")
	public String RenameGroups( @PathVariable("idGroup")Long idGroup , @PathVariable("Newname") String Newname ) {
		return groupService.RenameGroups(idGroup, Newname);
	}
	
	//no
	public String ChangeImageGroups(Long idGroup,String image){
		return null;
	}
	
	
	@GetMapping("/{idGroup}")
	public Groups getByIdGroup( @PathVariable("idGroup") Long idGroup ) {
		return groupService.getByIdGroup(idGroup);
		
	}

	@GetMapping("")
	public List<Groups> retrieveAllGroup() {
		return groupService.retrieveAllGroup();
	}
	
	
	
	@DeleteMapping("/removeUser/{idGroup}/{idUser}")
	@ResponseBody
	public String RemoveMemberFromGroups(@PathVariable("idGroup") Long idGroup,@PathVariable("idUser") Long idUser) {
		return groupService.RemoveMemberFromGroups(idGroup, idUser);
		}

	@PutMapping("/AddMemberToGroups/{idGroup}/{IdUser}")
	
	public String AddMemberToGroups(@PathVariable("idGroup") Long idGroup,@PathVariable("IdUser") List<Long> IdUser){
		return groupService.AddMemberToGroups(idGroup, IdUser);
	}
	
	public String RenameMember(Long idGroup,Long idUser,String newName){
		return null;
	}
	
}
