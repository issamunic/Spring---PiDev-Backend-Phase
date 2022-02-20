package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	


}