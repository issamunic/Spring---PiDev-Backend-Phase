package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.HistorySubcriptionCompany;
import tn.esprit.spring.entities.SubscriptionCompany;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.HistorySubcriptionCompanyRepository;
import tn.esprit.spring.repository.SubscriptionCompanyRepository;
import tn.esprit.spring.serviceInterface.ISubscriptionCompanyService;

@Service
@Slf4j
public class SubscriptionCompanyImpl implements ISubscriptionCompanyService {

	@Autowired
	SubscriptionCompanyRepository SubscriptionCompanyRepo;
	
	@Autowired 
	HistorySubcriptionCompanyRepository HistorySubcriptionCompanyRepo;
	
	
	@Override
	public List<SubscriptionCompany> getAll() {
		try {
			List<SubscriptionCompany> SubscriptionCompanys = (List<SubscriptionCompany>) SubscriptionCompanyRepo.findAll();
			for (SubscriptionCompany SubscriptionCompany : SubscriptionCompanys) {
				log.info(" SubscriptionCompany : " + SubscriptionCompany);
			}
			return SubscriptionCompanys;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public SubscriptionCompany add(SubscriptionCompany SubscriptionCompanynew) {
		try {
			SubscriptionCompanyRepo.save(SubscriptionCompanynew);
			return SubscriptionCompanynew;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public SubscriptionCompany update(SubscriptionCompany SubscriptionCompanynew) {
		try {
			SubscriptionCompanyRepo.save(SubscriptionCompanynew);
			return SubscriptionCompanynew;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long SubscriptionCompanyId) {
		try {
			SubscriptionCompanyRepo.deleteById(SubscriptionCompanyId);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}

	@Override
	public SubscriptionCompany getById(Long SubscriptionCompanyId) {
		try {
			SubscriptionCompany SubscriptionCompany = SubscriptionCompanyRepo.findById(SubscriptionCompanyId).orElse(null);
			return SubscriptionCompany;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	

	@Override
	public SubscriptionCompany InitilainitializationOfSubscriptionCompany(User Company) {
		try {
			HistorySubcriptionCompany HistorySubcriptionCompanynew = new HistorySubcriptionCompany();
			HistorySubcriptionCompanynew.setCompanyId(Company.getIdUser());
			HistorySubcriptionCompanynew.setNbrEmployeeAddedToMax(10);
			HistorySubcriptionCompanyRepo.save(HistorySubcriptionCompanynew);
			/////////////////////////////////////////////////////////////////////////////////////////////////
			SubscriptionCompany SubscriptionCompany = new SubscriptionCompany();
			SubscriptionCompany.setNbrEmployeeMax(10);
			SubscriptionCompany.setCompany(Company);
			
			return SubscriptionCompanyRepo.save(SubscriptionCompany);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	
	@Override
	public SubscriptionCompany getByUser(User Company) {
		try {
			List<SubscriptionCompany> SubscriptionCompanys = (List<SubscriptionCompany>) SubscriptionCompanyRepo.findAll();
			for (SubscriptionCompany SubscriptionCompany : SubscriptionCompanys) {
				if(SubscriptionCompany.getCompany().getIdUser().equals(Company.getIdUser())) {
					return SubscriptionCompany;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public SubscriptionCompany UpgradeSubscriptionCompany(SubscriptionCompany SubscriptionCompanynew) {
		try {
			SubscriptionCompany SubscriptionCompanyold = getById(SubscriptionCompanynew.getIdSubscriptionCompany());
			
			HistorySubcriptionCompany HistorySubcriptionCompanynew = new HistorySubcriptionCompany();
			HistorySubcriptionCompanynew.setCompanyId(SubscriptionCompanynew.getCompany().getIdUser());
			HistorySubcriptionCompanynew.setNbrEmployeeAddedToMax(SubscriptionCompanynew.getNbrEmployeeMax());
			HistorySubcriptionCompanyRepo.save(HistorySubcriptionCompanynew);
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			SubscriptionCompanynew.setNbrEmployeeMax(SubscriptionCompanyold.getNbrEmployeeMax()+SubscriptionCompanynew.getNbrEmployeeMax());
			SubscriptionCompanyRepo.save(SubscriptionCompanynew);
			return SubscriptionCompanynew;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	
}
