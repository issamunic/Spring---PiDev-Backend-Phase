package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Examen;

public interface IExamenService {

	List<Examen> retrieveAllExamens();

}
