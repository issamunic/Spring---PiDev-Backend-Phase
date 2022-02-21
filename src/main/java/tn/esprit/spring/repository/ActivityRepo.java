package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Activity;


@Repository
public interface ActivityRepo extends CrudRepository<Activity, Long> {
	

}
