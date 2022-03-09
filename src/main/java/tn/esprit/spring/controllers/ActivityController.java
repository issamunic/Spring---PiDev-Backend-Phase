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
import tn.esprit.spring.entities.TripPlan;
import tn.esprit.spring.repository.ActivityRepo;
import tn.esprit.spring.repository.TripPlanRepo;
import tn.esprit.spring.serviceInterface.IActivityService;

@RestController
@Api(tags = "Activity managment")
@RequestMapping("/Activity")
public class ActivityController {
	
	@Autowired
	IActivityService ActivityService;
	
	@Autowired
	ActivityRepo ar;
	
	@Autowired
	TripPlanRepo trp;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
	@ApiOperation(value = "getAllActivitys")
	@GetMapping("/getAll")
	@ResponseBody
	public List<Activity> getAll() {
		return ActivityService.getAll();
	}
	
	@ApiOperation(value = "addActivityByResponsable")
	@PostMapping("/addbyResp/{idTripPlan}")
	@ResponseBody
	public List<Activity> addActivityByResponsable(@RequestBody List<Activity> activities,@PathVariable("idTripPlan") int idTripPlan) {
		return ActivityService.addActivityByResponsable(activities, idTripPlan);
		
		
	}
	
	@ApiOperation(value = "addActivity")
	@PostMapping("/add/{idTripPlan}")
	@ResponseBody
	public String add(@RequestBody Activity activity, @PathVariable("idTripPlan") int idTripPlan) {
		 /*TripPlan tp = trp.findById(tripPlanId).orElse(null);*/
		 /*activity.setTripPlan(tp);*/
		 //ar.save(activity);
		 return ActivityService.add(activity,idTripPlan);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{Activity-id}")
	@ResponseBody
	public Activity getById(@PathVariable("Activity-id") Long id) {
		System.out.println("********************"+id+"**********");
		return ActivityService.getById(id);
	}
	
	@ApiOperation(value = "updateActivity")
	@PutMapping("/update/{tripPlanId}")
	@ResponseBody
	public void update(@PathVariable("tripPlanId") Long tripPlanId) {
		 ActivityService.update(tripPlanId);
	}
	
	
	
	
	/*@ApiOperation(value = "deleteActivity")
	@DeleteMapping("/delete/{Activity-id}")
	@ResponseBody
	public void delete(@PathVariable("Activity-id") Long id) {
		 ActivityService.delete(id);
	}
	
*/
	@ApiOperation(value = "ajouterActivityToTripPlan")
	@GetMapping("/asgTPAC/{tripPlan-id}/{activity-id}")
	@ResponseBody
	public void assgProjetToUser(@PathVariable("tripPlan-id") long tripPlanid ,@PathVariable("activity-id") long activityid) {
		ActivityService.addActivityToTripPlan(tripPlanid, activityid);
	}
		
		@ApiOperation(value = "find-Activities-ByTripPlan")
		@GetMapping("/find-Activities-ByTripPlan/{tripPlan-id}")
		@ResponseBody
		public List<Activity> findActivitiesByTripPlan(@PathVariable("tripPlan-id") long tripPlanid ) {
			return ActivityService.listActivityByTripPlanId(tripPlanid);
	
	}
		
		@ApiOperation(value = "makeActivityDone")
		@PutMapping("/done/{idActivity}")
		@ResponseBody
		public String makeActivityDone(@PathVariable("idActivity") Long idActivity) {
			 return ActivityService.makeDoneActivity(idActivity);
		}
}
