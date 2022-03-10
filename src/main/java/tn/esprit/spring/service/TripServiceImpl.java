package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Task;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.repository.TripRepository;
import tn.esprit.spring.serviceInterface.ITripService;

@Service
@Slf4j
public class TripServiceImpl implements ITripService {

	@Autowired
	TripRepository tripRepo;
	
	


	@Override
	public List<Trip> getAll() {
		try {
			List<Trip> trips = (List<Trip>) tripRepo.findAll();
			for (Trip trip : trips) {
				log.info(" Trip : " + trip);
			}
			return trips;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Trip add(Trip trip) {
		try {
			trip.setDestination(getDestination(trip.getLongitude(), trip.getLatitude()));

			return tripRepo.save(trip);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public Trip update(Trip trip) {
		try {
			tripRepo.save(trip);
			return trip;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long tripid) {
		try {
			tripRepo.deleteById(tripid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public Trip getById(Long tripid) {
		try {
			Trip trip = tripRepo.findById(tripid).orElse(null);
			return trip;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	

	@Override
	public List<Trip> getTripByDate(Date dateInf, Date dateSup) {
		return tripRepo.getTripByStartDate(dateInf, dateSup);
	}

	@Override
	public List<Trip> getTripInPeriod(Date dateInf, Date dateSup) {
		return tripRepo.getTripInPeriod(dateInf, dateSup);
	}

	@Override
	public List<Trip> getByName(String name) {
		try {
			List<Trip> trips = (List<Trip>) tripRepo.findByName(name);
			for (Trip trip : trips) {
				log.info(" Trip : " + trip);
			}
			return trips;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void apiCall() {
	   

	   
	
	    

		
	}

	

	@Override
	public List<Trip> getGroupedByDestination() {
		return tripRepo.getGroupedByDestination();
	}

	@Override
	public List<List<Trip>> groupBy(List<Trip> liste) {
		if (liste == null) return null;
		String destination ="";
		List<List<Trip>> parentList = new ArrayList<List<Trip>>();
		List<Trip> childList = new ArrayList<Trip>();
		destination = liste.get(0).getDestination();
		for ( Trip trip : liste) {
			if(trip.getDestination().equals(destination)) childList.add(trip);
			else {
				destination = trip.getDestination();
				parentList.add(childList);
				childList= new ArrayList<Trip>();
				childList.add(trip);
			}
		}
		parentList.add(childList);
		return parentList;
	}
	
	String getDestination(String longitude, String latitude) {
		String destination="";
		
		  String uri = "http://api.positionstack.com/v1/reverse?access_key=7f7711b22c8b37548fd956a27e4ce17a&query="+longitude+",-"+latitude;

		    RestTemplate restTemplate = new RestTemplate();
		    String result = restTemplate.getForObject(uri, String.class);
		    System.out.println(uri);
		   
		    
		    JSONObject jo = new JSONObject(result);
		    
		    JSONArray ja = jo.getJSONArray("data");
		    
		    jo = ja.getJSONObject(0);
		    
		    destination+=jo.getString("country");    
		    destination+=" ";

		    destination+=jo.getString("locality");
		    destination+=" ";

		    destination+=jo.getString("region");
		    destination+=" ";


		    
		    
		
		return destination;
	}
	
	

	

	
	

}