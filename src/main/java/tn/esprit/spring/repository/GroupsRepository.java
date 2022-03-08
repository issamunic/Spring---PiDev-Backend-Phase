package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.*;


@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long>{

//	@Query("SELECT g from Groups g where "
	//		+ "c..idUser=:idSession"
	//		+ "order By dateMsg desc ")
	//List<Groups> retrieveGroupByUser(Long idSession);
}