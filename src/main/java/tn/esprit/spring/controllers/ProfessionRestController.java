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
import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.serviceInterface.IProfessionService;

@RestController
@Api(tags = "profession management")
@RequestMapping("/profession")
public class ProfessionRestController {
	
	@Autowired
	IProfessionService professionService;
	
	@ApiOperation(value = "get list professions")
	@GetMapping("/retrieve-all-professions")
	@ResponseBody
	public List<Profession> getProfessions() {
		return professionService.retrieveAllProfessions();
	}
	
	@ApiOperation(value = "retrieve profession")
	@GetMapping("/retrieve-profession/{professionId}")
	@ResponseBody
	public Profession getProfession(@PathVariable("professionId") Long id) {
		return professionService.retrieveProfession(id);
	}
	
	@ApiOperation(value = "add profession")
	@PostMapping("/add-profession")
	@ResponseBody
	public Profession addProfession(@RequestBody Profession profession){
		return professionService.addProfession(profession);
	}
	
	@ApiOperation(value = "delete profession")
	@DeleteMapping("/remove-profession/{profession-id}")
	@ResponseBody
	public void deleteProfession(@PathVariable("profession-id") Long idProfession){
		professionService.deleteProfession(idProfession);
	}
	
	@ApiOperation(value = "update profession")
	@PutMapping("/modify-profession")
	@ResponseBody
	public Profession updateProfession(@RequestBody Profession profession){
		return professionService.updateProfession(profession);
	}
	
	
	
}
