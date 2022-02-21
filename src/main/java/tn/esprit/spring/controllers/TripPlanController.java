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
import tn.esprit.spring.entities.TripPlan;
import tn.esprit.spring.serviceInterface.ITripPlanService;

@RestController
@Api(tags = "TripPlan Manager")
@RequestMapping("/TripPlan")
public class TripPlanController {
	

	@Autowired
	ITripPlanService TripPlanService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
	@ApiOperation(value = "getAllTripPlans")
	@GetMapping("/getAll")
	@ResponseBody
	public List<TripPlan> getAll() {
		return TripPlanService.getAll();
	}
	
	
	@ApiOperation(value = "addTripPlan")
	@PostMapping("/add")
	@ResponseBody
	public TripPlan add(@RequestBody TripPlan TripPlan) {
		return TripPlanService.add(TripPlan);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{TripPlan-id}")
	@ResponseBody
	public TripPlan getById(@PathVariable("TripPlan-id") Long id) {
		System.out.println("********************"+id+"**********");
		return TripPlanService.getById(id);
	}
	
	@ApiOperation(value = "updateTripPlan")
	@PutMapping("/update")
	@ResponseBody
	public TripPlan update(@RequestBody TripPlan TripPlan) {
		return TripPlanService.update(TripPlan);
	}
	
	
	@ApiOperation(value = "deleteTripPlan")
	@DeleteMapping("/delete/{TripPlan-id}")
	@ResponseBody
	public void delete(@PathVariable("TripPlan-id") Long id) {
		 TripPlanService.delete(id);
	}
	
	
	
	

	

}
