package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.statistics.UserByRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findByRole(Role role);

	@Query("SELECT u FROM User u WHERE u.nameCompany LIKE %:name%")
	public List<User> findCompanysByName(@Param("name") String nameCompany);

	@Query("SELECT u FROM User u WHERE u.role LIKE 'employe' AND (CONCAT(trim(u.FirstNameEmploye), ' ', trim(u.LastNameEmploye)) LIKE %:name% OR CONCAT(trim(u.LastNameEmploye), ' ', trim(u.FirstNameEmploye)) LIKE %:name%)")
	public List<User> findEmployesByName(@Param("name") String nameEmploye);

	public List<User> findByDomain(Domain domain);

	@Query("SELECT u FROM User u WHERE u.BirthDateEmploye BETWEEN :dateDeb AND :dateFin AND u.role LIKE 'employe'")
	public List<User> getEmployesByDateRange(@Param("dateDeb") Date dateDebut, @Param("dateFin") Date dateFin);

	public User findByLogin(String login);

	@Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
	public User findByVerificationCode(String code);

	@Query("SELECT new tn.esprit.spring.statistics.UserByRole(u.role, COUNT(u)) FROM User u GROUP BY u.role")
	public List<UserByRole> numberOfUsersWithRole();
}
