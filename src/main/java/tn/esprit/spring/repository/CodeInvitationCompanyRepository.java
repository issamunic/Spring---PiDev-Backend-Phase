package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.CodeInvitationCompany;

@Repository
public interface CodeInvitationCompanyRepository extends JpaRepository<CodeInvitationCompany, Long>{

}
