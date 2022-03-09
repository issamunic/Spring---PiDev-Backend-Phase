package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.TripPlan;

@Repository
public interface TripPlanRepo extends CrudRepository<TripPlan, Long> {
	

}
