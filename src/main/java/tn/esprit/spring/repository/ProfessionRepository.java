package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Profession;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long>{

}
