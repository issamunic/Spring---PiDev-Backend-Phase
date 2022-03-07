package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.TripPlan;

public interface ITripPlanService {
public List<TripPlan> getAll();
	
	
	public TripPlan add(TripPlan TripPlan);
	
	public TripPlan update(TripPlan TripPlan);
	
	public void delete(Long TripPlanid);
	
	public TripPlan getById(Long TripPlanid);


}
