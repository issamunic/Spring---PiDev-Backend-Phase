package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.serviceInterface.IUserService;

@RestController
@Api(tags = "user management")
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	IUserService userService;
	
	@ApiOperation(value = "get list users")
	@GetMapping("/retrieve-all-users")
	@ResponseBody
	public List<User> getUsers() {
		return userService.retrieveAllUsers();
	}
	
	@ApiOperation(value = "retrieve user")
	@GetMapping("/retrieve-user/{userId}")
	@ResponseBody
	public User getUser(@PathVariable("userId") Long id) {
		return userService.retrieveUser(id);
	}
	
	@ApiOperation(value = "add user")
	@PostMapping("/add-user")
	@ResponseBody
	public User addUser(@RequestBody User user){
		return userService.addUser(user);
	}
	
	@ApiOperation(value = "delete user")
	@DeleteMapping("/remove-user/{user-id}")
	@ResponseBody
	public void deleteUser(@PathVariable("user-id") Long idUser){
		userService.deleteUser(idUser);
	}
	
	@ApiOperation(value = "update user")
	@PutMapping("/modify-user")
	@ResponseBody
	public User updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
}
