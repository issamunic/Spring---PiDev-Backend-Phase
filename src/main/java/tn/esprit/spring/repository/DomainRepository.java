package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.statistics.UserByDomain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long>{
	
	@Query("SELECT new tn.esprit.spring.statistics.UserByDomain(d.name, COUNT(u.idUser)) FROM User u,Domain d "
			+ "where u.domain.idDomain=d.idDomain GROUP BY d.name")
	public List<UserByDomain> numberOfUsersWithDomain();

}
