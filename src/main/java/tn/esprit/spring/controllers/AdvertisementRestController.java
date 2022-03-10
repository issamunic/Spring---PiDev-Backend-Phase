package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Advertisement;
import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.serviceInterface.IAdvertisementService;

@RestController
@Api(tags = "Gestion des publicités")
@RequestMapping("/advertisement")
public class AdvertisementRestController {
	
	
	@Autowired 
	IAdvertisementService adService;
	
	@ApiOperation(value = "add ad")
	@PostMapping("/add-ad")
	@ResponseBody
	public Advertisement addAd(@RequestBody Advertisement ad) throws Exception{
		return adService.Add(ad);
	}

}
