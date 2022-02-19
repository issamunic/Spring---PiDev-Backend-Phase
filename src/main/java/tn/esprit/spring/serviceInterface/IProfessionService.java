package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Profession;

public interface IProfessionService {

	public abstract List<Profession> retrieveAllProfessions();
	
	public abstract Profession addProfession(Profession profession);
	
	public abstract void deleteProfession(Long id);

	public abstract Profession updateProfession(Profession profession);

	public abstract Profession retrieveProfession(Long id);
}
