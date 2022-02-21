package tn.esprit.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Services.IGroupsService;
import tn.esprit.spring.entity.GroupeType;
import tn.esprit.spring.entity.Groups;
import tn.esprit.spring.entity.themes;

@RestController
@RequestMapping("/group")
public class GroupsControllers{
	@Autowired
	IGroupsService groupService;
	
	//no
	@PostMapping("/CreateGroups")
	public String CreateGroup(@RequestBody Groups g) {
		return groupService.CreateGroup(g);
	}
	
	// okey
	@DeleteMapping("/deleteGroups/{idGroup}")
		public void deleteGroup(@PathVariable("idGroup")Long idGroup) {
			groupService.deleteGroup(idGroup);
		}
		
		
		
	// okey
	@PutMapping("/upadateEtatGroup/{idGroup}/{etatGroup}")
	public void UpadateEtatGroup(@PathVariable("idGroup") Long idGroup,@PathVariable("etatGroup") GroupeType etatGroup) {
		groupService.UpadateEtatGroup(idGroup, etatGroup);
	}
	
	// okey
	@PutMapping("/updateTheme/{idGroup}/{theme}")
	public void ChangeThemeGroup(@PathVariable("idGroup")Long idGroup,@PathVariable("theme") themes theme) {
		groupService.ChangeThemeGroup(idGroup, theme);
	}
	// okey
	@PutMapping("/renameGroups/{idGroup}/{Newname}")
	public String RenameGroups(@PathVariable("idGroup")Long idGroup,@PathVariable("Newname") String Newname) {
		return groupService.RenameGroups(idGroup, Newname);
	}
	
	//no
	public String ChangeImageGroups(Long idGroup,String image){
		return null;
	}
	
	@DeleteMapping("/removeUser/{idGroup}/{idUser}")
	@ResponseBody
	public String RemoveMemberFromGroups(@PathVariable("idGroup") Long idGroup,@PathVariable("idUser") Long idUser) {
		return groupService.RemoveMemberFromGroups(idGroup, idUser);
		}

	@PutMapping("/AddMemberToGroups/{idGroup}/{IdUser}")
	
	public String AddMemberToGroups(Long idGroup,List<Long> IdUser){
		return null;
	}
	public String MemberLeaveGroups(Long idGroup,Long idUser){
		return null;
	}
	public String RenameMember(Long idGroup,Long idUser,String newName){
		return null;
	}
	
}
