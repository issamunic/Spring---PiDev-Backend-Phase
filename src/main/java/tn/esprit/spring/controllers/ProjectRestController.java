package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Project;
import tn.esprit.spring.serviceInterface.IProjectService;
@RestController
@Api(tags = "Project Manager")
@RequestMapping("/project")
public class ProjectRestController {

	@Autowired
	IProjectService projectService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
	@ApiOperation(value = "getAllProjects")
	@GetMapping("/getAll")
	@ResponseBody
	public List<Project> getAll() {
		return projectService.getAll();
	}
	
	
	@ApiOperation(value = "addProject")
	@PostMapping("/add")
	@ResponseBody
	public Project add(@RequestBody Project project) {
		return projectService.add(project);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{project-id}")
	@ResponseBody
	public Project getById(@PathVariable("project-id") Long id) {
		System.out.println("********************"+id+"**********");
		return projectService.getById(id);
	}
	
	@ApiOperation(value = "updateProject")
	@PutMapping("/update")
	@ResponseBody
	public Project update(@RequestBody Project project) {
		return projectService.update(project);
	}
	
	
	@ApiOperation(value = "deleteProject")
	@DeleteMapping("/delete/{project-id}")
	@ResponseBody
	public void delete(@PathVariable("project-id") Long id) {
		 projectService.delete(id);
	}
	
	
	@ApiOperation(value = "getByUser")
	@GetMapping("/getByUser/{user-id}")
	@ResponseBody
	public Project getByUser(@PathVariable("user-id") Long id) {
		System.out.println("********************"+id+"**********");
		return projectService.getById(id);
	}
	
	@ApiOperation(value = "getByDate")
	@GetMapping("/getByDate/{date}")
	@ResponseBody
	public Project getBydate(@PathVariable("date") Long id) {
		System.out.println("********************"+id+"**********");
		return projectService.getById(id);
	}
	
	
	
	

	


}
	