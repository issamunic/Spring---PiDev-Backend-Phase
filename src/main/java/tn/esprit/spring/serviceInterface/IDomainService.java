package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Domain;

public interface IDomainService {

	public abstract List<Domain> retrieveAllDomains();
	
	public abstract Domain addDomain(Domain domain);
	
	public abstract void deleteDomain(Long id);

	public abstract Domain updateDomain(Domain domain);

	public abstract Domain retrieveDomain(Long id);
}
