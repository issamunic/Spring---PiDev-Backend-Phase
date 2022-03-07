package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.TripPlan;
import tn.esprit.spring.repository.TripPlanRepo;
import tn.esprit.spring.serviceInterface.ITripPlanService;

@Service
@Slf4j
public class TripPlanService implements ITripPlanService {
	
	@Autowired
	TripPlanRepo TripPlanRepo;

	@Override
	public List<TripPlan> getAll() {
		try {
			List<TripPlan> TripPlans = (List<TripPlan>) TripPlanRepo.findAll();
			for (TripPlan TripPlan : TripPlans) {
				log.info(" TripPlan : " + TripPlan);
			}
			return TripPlans;
		} catch (Exception e) {
			return null;
		}
	}
	@Override
	public TripPlan add(TripPlan TripPlan) {
		try {
			return TripPlanRepo.save(TripPlan);
			
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public TripPlan update(TripPlan TripPlan) {
		try {
			TripPlanRepo.save(TripPlan);
			return TripPlan;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long TripPlanid) {
		try {
			TripPlanRepo.deleteById(TripPlanid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public TripPlan getById(Long TripPlanid) {
		try {
			TripPlan TripPlan = TripPlanRepo.findById(TripPlanid).orElse(null);
			return TripPlan;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

}
