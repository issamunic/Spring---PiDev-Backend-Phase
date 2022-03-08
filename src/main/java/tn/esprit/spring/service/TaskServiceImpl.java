package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Task;
import tn.esprit.spring.repository.TaskRepository;
import tn.esprit.spring.serviceInterface.ITaskService;

@Service
@Slf4j
public class TaskServiceImpl implements ITaskService {

	@Autowired
	TaskRepository taskRepo;

	@Override
	public List<Task> getAll() {
		try {
			List<Task> tasks = (List<Task>) taskRepo.findAll();
			for (Task task : tasks) {
				log.info(" Task : " + task);
			}
			return tasks;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Task add(Task task) {
		try {
			return taskRepo.save(task);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public Task update(Task task) {
		try {
			taskRepo.save(task);
			return task;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long taskid) {
		try {
			taskRepo.deleteById(taskid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public Task getById(Long taskid) {
		try {
			Task task = taskRepo.findById(taskid).orElse(null);
			return task;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}
	
	
	
}