package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.*;


@Repository
public interface GroupsRepository extends CrudRepository<Groups, Long>{
}