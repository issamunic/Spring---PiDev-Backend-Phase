package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Trip;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	
	@Query("SELECT t FROM Trip t WHERE t.startDate BETWEEN :dateInf AND :dateSup")
	List<Trip> getTripByStartDate(@Param("dateInf") Date dateInf,@Param("dateSup") Date dateSup);

	
	@Query("SELECT t FROM Trip t WHERE t.startDate > :dateInf AND t.endDate < :dateSup  ")
	List<Trip> getTripInPeriod(@Param("dateInf") Date dateInf,@Param("dateSup") Date dateSup);
	
	public List<Trip> findByName(String name);
	
	@Query("SELECT t FROM Trip t ORDER BY destination  ")
	public List<Trip> getGroupedByDestination();
}
