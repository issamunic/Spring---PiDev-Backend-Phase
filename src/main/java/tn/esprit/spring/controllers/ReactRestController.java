package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.dto.ReactDto;
import tn.esprit.spring.serviceInterface.IReactService;

@RestController
@Api(tags = "Reactions")
@RequestMapping("/react")
public class ReactRestController {
	
	@Autowired
	IReactService reactService;
	
	@ApiOperation(value = "add react")
	@PostMapping("/add-react")
	@ResponseBody
	public void addReact(@RequestBody ReactDto reactDto){
		 reactService.React(reactDto);
	}

}
