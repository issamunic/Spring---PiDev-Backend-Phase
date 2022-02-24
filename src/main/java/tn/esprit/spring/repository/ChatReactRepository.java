package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.ChatReact;


@Repository
public interface ChatReactRepository extends CrudRepository<ChatReact, Long>{
		
}