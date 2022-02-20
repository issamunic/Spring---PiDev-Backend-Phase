package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Task;
import tn.esprit.spring.serviceInterface.ITaskService;
@RestController
@Api(tags = "Task Manager")
@RequestMapping("/task")
public class TaskRestController {

	@Autowired
	ITaskService taskService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
	@ApiOperation(value = "getAllTasks")
	@GetMapping("/getAll")
	@ResponseBody
	public List<Task> getAll() {
		return taskService.getAll();
	}
	
	
	@ApiOperation(value = "addTask")
	@PostMapping("/add")
	@ResponseBody
	public Task add(@RequestBody Task task) {
		return taskService.add(task);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{task-id}")
	@ResponseBody
	public Task getById(@PathVariable("task-id") Long id) {
		System.out.println("********************"+id+"**********");
		return taskService.getById(id);
	}
	
	@ApiOperation(value = "updateTask")
	@PutMapping("/update")
	@ResponseBody
	public Task update(@RequestBody Task task) {
		return taskService.update(task);
	}
	
	
	@ApiOperation(value = "deleteTask")
	@DeleteMapping("/delete/{task-id}")
	@ResponseBody
	public void delete(@PathVariable("task-id") Long id) {
		 taskService.delete(id);
	}
	
	
	
	

	


}
