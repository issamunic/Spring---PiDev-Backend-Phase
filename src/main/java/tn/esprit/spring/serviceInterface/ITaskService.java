package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Task;

public interface ITaskService {

	public List<Task> getAll();
	
	
	public Task add(Task task);
	
	public Task update(Task task);
	
	public void delete(Long taskid);
	
	public Task getById(Long taskid);


}
