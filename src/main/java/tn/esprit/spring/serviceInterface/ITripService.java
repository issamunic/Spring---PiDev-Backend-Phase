package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Trip;

public interface ITripService {

	public List<Trip> getAll();
	
	
	public Trip add(Trip trip);
	
	public Trip update(Trip trip);
	
	public void delete(Long tripid);
	
	public Trip getById(Long tripid);


}
