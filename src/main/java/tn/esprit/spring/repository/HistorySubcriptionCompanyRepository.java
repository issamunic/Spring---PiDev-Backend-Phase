package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.HistorySubcriptionCompany;
import tn.esprit.spring.entities.SubscriptionCompany;


@Repository
public interface HistorySubcriptionCompanyRepository extends JpaRepository<HistorySubcriptionCompany, Long>{

}
