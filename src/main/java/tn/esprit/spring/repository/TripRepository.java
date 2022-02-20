package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Trip;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	


}
