package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.SubscriptionCompany;
import tn.esprit.spring.entities.User;

public interface ISubscriptionCompanyService {

	List<SubscriptionCompany> getAll();

	SubscriptionCompany update(SubscriptionCompany SubscriptionCompany);

	SubscriptionCompany getById(Long SubscriptionCompanyId);

	void delete(Long SubscriptionCompanyId);

	SubscriptionCompany add(SubscriptionCompany SubscriptionCompanynew);

	SubscriptionCompany getByUser(User Company);
	
	SubscriptionCompany InitilainitializationOfSubscriptionCompany(User Company);

	SubscriptionCompany UpgradeSubscriptionCompany(SubscriptionCompany SubscriptionCompanynew);

}
