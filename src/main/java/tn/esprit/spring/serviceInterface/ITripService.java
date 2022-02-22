package tn.esprit.spring.serviceInterface;

import java.util.Date;
import java.util.List;

import tn.esprit.spring.entities.Trip;

public interface ITripService {

	public List<Trip> getAll();
	
	
	public Trip add(Trip trip);
	
	public Trip update(Trip trip);
	
	public void delete(Long tripid);
	
	public Trip getById(Long tripid);
	
	public List<Trip> getTripByDate(Date dateInf, Date dateSup);
	
	public List<Trip> getTripInPeriod(Date dateInf, Date dateSup);
	
	public List<Trip> getByName(String name) ;


}
