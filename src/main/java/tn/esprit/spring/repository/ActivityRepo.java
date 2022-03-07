package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Activity;
import tn.esprit.spring.entities.TripPlan;


@Repository
public interface ActivityRepo extends CrudRepository<Activity, Long> {
	
	@Query
	("SELECT a From Activity a Where a.endActivity < CURRENT_TIMESTAMP ")
	List<Activity> ListActivityInfCurentDate();
	
	@Query
	("SELECT a From Activity a Where a.tripPlan = :id ")
	List<Activity> ListActivityByIdTripplan(@Param("id") TripPlan id);
	
	@Query
	("SELECT a From Activity a Where (a.startActivity IS  NULL AND a.endActivity IS  NULL AND a.tripPlan = :id)")
	List<Activity> ListActivityWithoutDate(@Param("id") TripPlan id);
	
	
	
	

}
