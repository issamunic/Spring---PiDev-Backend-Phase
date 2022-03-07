package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.SubscriptionCompany;

@Repository
public interface SubscriptionCompanyRepository extends JpaRepository<SubscriptionCompany, Long>{

}
