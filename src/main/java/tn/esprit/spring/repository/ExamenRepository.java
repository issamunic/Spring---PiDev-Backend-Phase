package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Examen;


@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {


}
