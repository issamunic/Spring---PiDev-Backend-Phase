package tn.esprit.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Report;


public interface RepportRepository extends CrudRepository<Report, Integer> {

}
