package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Activity;

public interface IActivityService {

	
public List<Activity> getAll();
	
	
	public String add(Activity Activity, int idTripPlan);
	
	public void update(long idTripPlan);
	
	public void delete();
	
	public Activity getById(Long Activityid);

	public List<Activity> listActivityByTripPlanId(long id);
	public List<Activity> addActivityByResponsable(List<Activity> activities, int id);

	void addActivityToTripPlan(long idTripPlan, long idActivity);
	
	public String makeDoneActivity(long id);
	public void makeNotDoneActivity();
	
}
