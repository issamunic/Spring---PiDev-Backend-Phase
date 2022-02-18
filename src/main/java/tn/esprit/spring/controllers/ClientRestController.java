package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Examen;
import tn.esprit.spring.serviceInterface.IExamenService;
@RestController
@Api(tags = "Gestion des examens")
@RequestMapping("/examen")
public class ClientRestController {

	@Autowired
	IExamenService examenService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	@ApiOperation(value = "Récupérer la liste des examens")
	@GetMapping("/retrieve-all-examens")
	@ResponseBody
	public List<Examen> getClients() {
		return examenService.retrieveAllExamens();
	}

	


}
