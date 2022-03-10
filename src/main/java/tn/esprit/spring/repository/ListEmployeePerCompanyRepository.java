package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.ListEmployeePerCompany;

@Repository
public interface ListEmployeePerCompanyRepository  extends JpaRepository<ListEmployeePerCompany, Long>{

}
