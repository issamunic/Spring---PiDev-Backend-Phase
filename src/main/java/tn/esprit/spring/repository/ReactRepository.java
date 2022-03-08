package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.React;
import tn.esprit.spring.entities.User;

@Repository
public interface ReactRepository extends JpaRepository<React, Long> {
	Optional<React> findTopByPostAndUserOrderByIdReactDesc(Post post, User currentUser);
}
