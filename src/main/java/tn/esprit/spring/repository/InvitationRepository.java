package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long>{

}
