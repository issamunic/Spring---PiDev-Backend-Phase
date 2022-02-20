package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.repository.ProjectRepository;
import tn.esprit.spring.serviceInterface.IProjectService;

@Service
@Slf4j
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	ProjectRepository projectRepo;

	@Override
	public List<Project> getAll() {
		try {
			List<Project> projects = (List<Project>) projectRepo.findAll();
			for (Project project : projects) {
				log.info(" Project : " + project);
			}
			return projects;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Project add(Project project) {
		try {
			return projectRepo.save(project);
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public Project update(Project project) {
		try {
			projectRepo.save(project);
			return project;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public void delete(Long projectid) {
		try {
			projectRepo.deleteById(projectid);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	@Override
	public Project getById(Long projectid) {
		try {
			Project project = projectRepo.findById(projectid).orElse(null);
			return project;
		}catch(Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

}