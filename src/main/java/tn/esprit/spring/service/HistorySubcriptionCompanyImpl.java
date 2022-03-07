package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.HistorySubcriptionCompany;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.repository.HistorySubcriptionCompanyRepository;
import tn.esprit.spring.serviceInterface.IHistorySubcriptionCompanyService;


@Service
@Slf4j
public class HistorySubcriptionCompanyImpl  implements IHistorySubcriptionCompanyService {

	
	@Autowired
	HistorySubcriptionCompanyRepository HistorySubcriptionCompanyRepo;
	
	
	@Override
	public List<HistorySubcriptionCompany> getAll() {
		try {
			List<HistorySubcriptionCompany> HistorySubcriptionCompanys = (List<HistorySubcriptionCompany>) HistorySubcriptionCompanyRepo.findAll();
			for (HistorySubcriptionCompany HistorySubcriptionCompany : HistorySubcriptionCompanys) {
				log.info(" HistorySubcriptionCompany : " + HistorySubcriptionCompany);
			}
			return HistorySubcriptionCompanys;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public HistorySubcriptionCompany add(HistorySubcriptionCompany HistorySubcriptionCompanynew) {
		try {
			HistorySubcriptionCompanynew.setDateHistorySubcriptionCompany(new Date());
			HistorySubcriptionCompanyRepo.save(HistorySubcriptionCompanynew);
			return HistorySubcriptionCompanynew;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public HistorySubcriptionCompany update(HistorySubcriptionCompany HistorySubcriptionCompanynew) {
		try {
			HistorySubcriptionCompanyRepo.save(HistorySubcriptionCompanynew);
			return HistorySubcriptionCompanynew;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long HistorySubcriptionCompanyId) {
		try {
			HistorySubcriptionCompanyRepo.deleteById(HistorySubcriptionCompanyId);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}

	@Override
	public HistorySubcriptionCompany getById(Long HistorySubcriptionCompanyId) {
		try {
			HistorySubcriptionCompany HistorySubcriptionCompany = HistorySubcriptionCompanyRepo.findById(HistorySubcriptionCompanyId).orElse(null);
			return HistorySubcriptionCompany;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<HistorySubcriptionCompany> getByCompany(Long id) {
		try {
			List<HistorySubcriptionCompany> historySubcriptionCompaniesfinal  = new ArrayList();			
			List<HistorySubcriptionCompany> HistorySubcriptionCompanys = (List<HistorySubcriptionCompany>) HistorySubcriptionCompanyRepo.findAll();
			for (HistorySubcriptionCompany HistorySubcriptionCompanyfor : HistorySubcriptionCompanys) {
				if(id.equals(HistorySubcriptionCompanyfor.getCompanyId())) {
					historySubcriptionCompaniesfinal.add(HistorySubcriptionCompanyfor);
				}
			}
			return historySubcriptionCompaniesfinal;
			
	}
		catch (Exception e) {
			System.out.println(e);
		return null;
	}
	}

}
