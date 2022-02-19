package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Profession;
import tn.esprit.spring.repository.ProfessionRepository;
import tn.esprit.spring.serviceInterface.IProfessionService;

@Service
@Slf4j
public class ProfessionServiceImpl implements IProfessionService{
	
	@Autowired
	ProfessionRepository professionRepository;
	
	@Override
	public List<Profession> retrieveAllProfessions() {
		List<Profession> professions=professionRepository.findAll();
		for(Profession profession:professions) {
			log.info("domain : "+profession);
		}
		return professions;
	}

	@Override
	public Profession addProfession(Profession profession) {
		try {
			Profession p=professionRepository.save(profession);
			log.info("profession added : "+p);
			return p;
		}
		catch(Exception e) {
			log.info("erreur domain add : "+e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteProfession(Long id) {
		try {
			professionRepository.deleteById(id);
			log.info("profession deleted with id: "+id);
		}
		catch(Exception e) {
			log.info("erreur profession delete : "+id);
		}
	}

	@Override
	public Profession updateProfession(Profession profession) {
		try {
			Profession p=professionRepository.save(profession);
			log.info("profession update : "+p);
			return p;
		}
		catch(Exception e) {
			log.info("erreur profession update : "+e.getMessage());
			return null;
		}
	}

	@Override
	public Profession retrieveProfession(Long id) {
		try {
			Profession p=professionRepository.findById(id).orElse(null);
			log.info("profession retrieve : "+p);
			return p;
		}
		catch(Exception e) {
			log.info("erreur profession retrieve : "+e.getMessage());
			return null;
		}
	}

}
