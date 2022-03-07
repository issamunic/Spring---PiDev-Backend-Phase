package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.RelationDomain;


@Repository
public interface RelationDomainRepository extends JpaRepository<RelationDomain,Long>{

}
