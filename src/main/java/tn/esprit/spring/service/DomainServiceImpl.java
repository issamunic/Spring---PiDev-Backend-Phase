package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Domain;
import tn.esprit.spring.repository.DomainRepository;
import tn.esprit.spring.serviceInterface.IDomainService;

@Service
@Slf4j
public class DomainServiceImpl implements IDomainService{
	
	@Autowired
	DomainRepository domainRepository;
	
	@Override
	public List<Domain> retrieveAllDomains() {
		List<Domain> domains=domainRepository.findAll();
		for(Domain domain:domains) {
			log.info("domain : "+domain);
		}
		return domains;
	}

	@Override
	public Domain addDomain(Domain domain) {
		try {
			Domain d=domainRepository.save(domain);
			log.info("domain added : "+d);
			return d;
		}
		catch(Exception e) {
			log.info("erreur domain add : "+e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteDomain(Long id) {
		try {
			domainRepository.deleteById(id);
			log.info("domain deleted with id: "+id);
		}
		catch(Exception e) {
			log.info("erreur domain delete : "+id);
		}
		
	}

	@Override
	public Domain updateDomain(Domain domain) {
		try {
			Domain d=domainRepository.save(domain);
			log.info("domain update : "+d);
			return d;
		}
		catch(Exception e) {
			log.info("erreur domain update : "+e.getMessage());
			return null;
		}
	}

	@Override
	public Domain retrieveDomain(Long id) {
		try {
			Domain d=domainRepository.findById(id).orElse(null);
			log.info("domain retrieve : "+d);
			return d;
		}
		catch(Exception e) {
			log.info("erreur domain retrieve : "+e.getMessage());
			return null;
		}
	}

}
