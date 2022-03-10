package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.ListEmployeePerCompany;
import tn.esprit.spring.entities.User;

public interface IListEmployeePerCompany {

	List<ListEmployeePerCompany> getAll();

	ListEmployeePerCompany add(ListEmployeePerCompany ListEmployeePerCompany);

	ListEmployeePerCompany update(ListEmployeePerCompany ListEmployeePerCompany);

	void delete(Long ListEmployeePerCompanyid);

	ListEmployeePerCompany getById(Long ListEmployeePerCompanyid);

	User getComany(Long id);

	List<User> getEmployees(Long id);

}
