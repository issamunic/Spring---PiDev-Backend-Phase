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
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.Service.StorieService;
import tn.esprit.spring.entities.Chat;
import tn.esprit.spring.entities.Stories;
import tn.esprit.spring.entities.User;

@RestController
@RequestMapping("/Stories")
@Slf4j
public class StoriesController{
	@Autowired
	StorieService StorieService;
	
	
	@PostMapping("/addStorie")
	public Stories CreateStorie(@RequestBody Stories storie){
		return StorieService.CreateStorie(storie);
	}
	
	@GetMapping("/getMyArchiveStories")
	public List<Stories> getMyArchiveStories(){
		return StorieService.getMyArchiveStories();
	}
	
	@GetMapping("/getStorieByUser/{idUser}")
	public List<Stories> getStorieByUser(@PathVariable("idUser") Long idUser){
		return StorieService.getStorieByUser(idUser);
	}
	
	@GetMapping("getFollowersStorie")
	public Map<User,List<Stories>> getFollowersStorie(){
		return StorieService.getFollowersStorie();
	}
	
	@PostMapping("/ban/{idStorie}/{idUser}")
	public void BanUserToShowStorie(@PathVariable("idStorie") Long idStorie ,@PathVariable("idUser") Long idUser){
		StorieService.BanUserToShowStorie(idStorie, idUser);
	}
	
	@DeleteMapping("unban/{idStorie}/{idUser}")
	public void unbannedUserToShowStorie(@PathVariable("idStorie") Long idStorie ,@PathVariable("idUser") Long idUser){
		StorieService.unbannedUserToShowStorie(idStorie, idUser);
	}
	
	@DeleteMapping("deleteSoty/{idStorie}")
	public void deleteSoty(@PathVariable("idStorie") Long idStorie){
		StorieService.deleteSoty(idStorie);
	}
	
	@PostMapping("repeat/{idGroup}")
	public void repeatToStorie( @PathVariable("idGroup") Long idGroup,@RequestBody Chat message){
		StorieService.repeatToStorie(idGroup,message);
	}
	

	
}
