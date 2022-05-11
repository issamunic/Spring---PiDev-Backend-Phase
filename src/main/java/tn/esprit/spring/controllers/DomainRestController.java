package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.serviceInterface.IDomainService;

@RestController
@Api(tags = "domain management")
@RequestMapping("/domain")
@CrossOrigin
public class DomainRestController {
	
	@Autowired
	IDomainService domainService;
	
	@ApiOperation(value = "get list domains")
	@GetMapping("/retrieve-all-domains")
	@ResponseBody
	public List<Domain> getDomains() {
		return domainService.retrieveAllDomains();
	}
	
	@ApiOperation(value = "retrieve domain")
	@GetMapping("/retrieve-domain/{domainId}")
	@ResponseBody
	public Domain getDomain(@PathVariable("domainId") Long id) {
		return domainService.retrieveDomain(id);
	}
	
	@ApiOperation(value = "add domain")
	@PostMapping("/add-domain")
	@ResponseBody
	public Domain addDomain(@RequestBody Domain domain){
		return domainService.addDomain(domain);
	}
	
	@ApiOperation(value = "delete domain")
	@DeleteMapping("/remove-domain/{domain-id}")
	@ResponseBody
	public void deleteDomain(@PathVariable("domain-id") Long idDomain){
		domainService.deleteDomain(idDomain);
	}
	
	@ApiOperation(value = "update domain")
	@PutMapping("/modify-domain")
	@ResponseBody
	public Domain updateDomain(@RequestBody Domain domain){
		return domainService.updateDomain(domain);
	}
	
	
	
}
