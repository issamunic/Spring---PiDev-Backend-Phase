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
import tn.esprit.spring.entities.Activity;
import tn.esprit.spring.serviceInterface.IActivityService;

@RestController
@Api(tags = "Activity managment")
@RequestMapping("/Activity")
public class ActivityController {
	
	@Autowired
	IActivityService ActivityService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
	@ApiOperation(value = "getAllActivitys")
	@GetMapping("/getAll")
	@ResponseBody
	public List<Activity> getAll() {
		return ActivityService.getAll();
	}
	
	
	@ApiOperation(value = "addActivity")
	@PostMapping("/add")
	@ResponseBody
	public Activity add(@RequestBody Activity Activity) {
		return ActivityService.add(Activity);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{Activity-id}")
	@ResponseBody
	public Activity getById(@PathVariable("Activity-id") Long id) {
		System.out.println("********************"+id+"**********");
		return ActivityService.getById(id);
	}
	
	@ApiOperation(value = "updateActivity")
	@PutMapping("/update")
	@ResponseBody
	public Activity update(@RequestBody Activity Activity) {
		return ActivityService.update(Activity);
	}
	
	
	@ApiOperation(value = "deleteActivity")
	@DeleteMapping("/delete/{Activity-id}")
	@ResponseBody
	public void delete(@PathVariable("Activity-id") Long id) {
		 ActivityService.delete(id);
	}
	

}
