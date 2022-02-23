package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Activity;

public interface IActivityService {

	
public List<Activity> getAll();
	
	
	public Activity add(Activity Activity);
	
	public Activity update(Activity Activity);
	
	public void delete(Long Activityid);
	
	public Activity getById(Long Activityid);

	
}
