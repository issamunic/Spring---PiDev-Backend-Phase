package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Project;

public interface IProjectService {

	public List<Project> getAll();
	
	
	public Project add(Project Project);
	
	public Project update(Project Project);
	
	public void delete(Long Projectid);
	
	public Project getById(Long Projectid);




}
