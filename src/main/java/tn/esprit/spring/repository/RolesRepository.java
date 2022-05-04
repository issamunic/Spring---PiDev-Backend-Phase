package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long>{
	
	public Roles findByRoleName(String roleName);
	
}
