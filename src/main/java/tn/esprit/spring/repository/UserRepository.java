package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public List<User> findByRole(Role role);
	
	@Query("SELECT u FROM User u WHERE u.nameCompany LIKE %:name%")
	public List<User> findCompanysByName(@Param("name") String nameCompany);
	
	@Query("SELECT u FROM User u WHERE u.FirstNameEmploye LIKE %:name% OR u.LastNameEmploye LIKE %:name%")
	public List<User> findEmployesByName(@Param("name") String nameEmploye);
}
