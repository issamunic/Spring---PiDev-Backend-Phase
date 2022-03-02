package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.HistorySubcriptionCompany;

public interface IHistorySubcriptionCompanyService {

	List<HistorySubcriptionCompany> getAll();

	HistorySubcriptionCompany add(HistorySubcriptionCompany HistorySubcriptionCompanynew);

	HistorySubcriptionCompany update(HistorySubcriptionCompany HistorySubcriptionCompanynew);

	void delete(Long HistorySubcriptionCompanyId);

	HistorySubcriptionCompany getById(Long HistorySubcriptionCompanyId);

	List<HistorySubcriptionCompany> getByCompany(Long id);

}
