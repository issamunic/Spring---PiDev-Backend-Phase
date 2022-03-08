package tn.esprit.spring.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

		
		tripService.apiCall();

		return tripService.getAll();
	}
	
	
	@ApiOperation(value = "addTrip")
	@PostMapping("/add")
	@ResponseBody
	public Trip add(@RequestBody Trip trip) {
		return tripService.add(trip);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{trip-id}")
	@ResponseBody
	public Trip getById(@PathVariable("trip-id") Long id) {
		System.out.println("********************"+id+"**********");
		return tripService.getById(id);
	}
	
	@ApiOperation(value = "updateTrip")
	@PutMapping("/update")
	@ResponseBody
	public Trip update(@RequestBody Trip trip) {
		return tripService.update(trip);
	}
	
	
	@ApiOperation(value = "deleteTrip")
	@DeleteMapping("/delete/{trip-id}")
	@ResponseBody
	public void delete(@PathVariable("trip-id") Long id) {
		 tripService.delete(id);
	}
	
	
	@ApiOperation(value = "getTripsByStartDate")
	@GetMapping("/getTripsByStartDate/{dateInf}/{dateSup}")
	@ResponseBody
	public List<Trip> getTripsByStartDate(@PathVariable("dateInf") String dateInf,
			@PathVariable("dateSup") String dateSup)  throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse(dateInf);
		Date dateFin = dateFormat.parse(dateSup);
		return tripService.getTripByDate(dateDebut, dateFin);
	}
	
	@ApiOperation(value = "getTripsInPeriod")
	@GetMapping("/getTripsInPeriod/{dateInf}/{dateSup}")
	@ResponseBody
	public List<Trip> getTripsInPeriod(@PathVariable("dateInf") String dateInf,
			@PathVariable("dateSup") String dateSup) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse(dateInf);
		Date dateFin = dateFormat.parse(dateSup);
		
		return tripService.getTripInPeriod(dateDebut, dateFin);
	}
	
	@ApiOperation(value = "get Trips Grouped By Destination")
	@GetMapping("/getGroupedByDestination")
	@ResponseBody
	public List<List<Trip>> getGroupedByDestination() {



		return tripService.groupBy(tripService.getGroupedByDestination()) ;
	}
	
	

	
	
	
	
	

	


}
