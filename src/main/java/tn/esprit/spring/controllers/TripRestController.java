package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.serviceInterface.ITripService;
@RestController
@Api(tags = "Trip Manager")
@RequestMapping("/trip")
public class TripRestController {

	@Autowired
	ITripService tripService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
	@ApiOperation(value = "getAllTrips")
	@GetMapping("/getAll")
	@ResponseBody
	public List<Trip> getAll() {
		return tripService.getAll();
	}
	
	
	@ApiOperation(value = "addTrip")
	@PostMapping("/add")
	@ResponseBody
	public Trip add(@RequestBody Trip trip) {
		return tripService.add(trip);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById")
	@ResponseBody
	public Trip getById(@RequestBody Long id) {
		return tripService.getById(id);
	}
	
	@ApiOperation(value = "updateTrip")
	@PutMapping("/update")
	@ResponseBody
	public Trip update(@RequestBody Trip trip) {
		return tripService.update(trip);
	}
	
	
	@ApiOperation(value = "deleteTrip")
	@PutMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody Long id) {
		 tripService.delete(id);
	}
	
	
	
	

	


}
