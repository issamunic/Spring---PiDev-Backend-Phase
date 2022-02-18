package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Examen;
import tn.esprit.spring.repository.ExamenRepository;
import tn.esprit.spring.serviceInterface.IExamenService;

@Service
@Slf4j
public class ExamenServiceImpl implements IExamenService {

	@Autowired
	ExamenRepository examenRepository;

	@Override
	public List<Examen> retrieveAllExamens() {
		List<Examen> examens = (List<Examen>) examenRepository.findAll();
		for (Examen examen : examens) {
			log.info(" Examen : " + examen);
		}
		return examens;
	}
}