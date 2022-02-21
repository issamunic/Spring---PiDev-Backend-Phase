package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.React;

@Repository
public interface ReactRepository extends JpaRepository<React, Long> {

}
