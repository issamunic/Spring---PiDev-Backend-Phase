package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
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

}