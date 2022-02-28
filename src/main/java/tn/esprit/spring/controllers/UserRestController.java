package tn.esprit.spring.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import tn.esprit.spring.entities.Role;
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
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@ApiOperation(value = "delete user")
	@DeleteMapping("/remove-user/{user-id}")
	@ResponseBody
	public void deleteUser(@PathVariable("user-id") Long idUser) {
		userService.deleteUser(idUser);
	}

	@ApiOperation(value = "update user")
	@PutMapping("/modify-user")
	@ResponseBody
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@ApiOperation(value = "assign user to domain")
	@PutMapping("/assign-user-to-domain/{user-id}/{domain-id}")
	@ResponseBody
	public void assignUserToDomain(@PathVariable("user-id") Long idUser, @PathVariable("domain-id") Long idDomain) {
		userService.assignUserToDomain(idUser, idDomain);
	}

	@ApiOperation(value = "assign user to profession")
	@PutMapping("/assign-user-to-profession/{user-id}/{profession-id}")
	@ResponseBody
	public void assignUserToProfession(@PathVariable("user-id") Long idUser,
			@PathVariable("profession-id") Long idProfession) {
		userService.assignUserToProfession(idUser, idProfession);
	}

	@ApiOperation(value = "get users by role")
	@GetMapping("/retrieve-users-with-role/{role}")
	@ResponseBody
	public List<User> getUsersWithRole(@PathVariable("role") Role role) {
		return userService.findUsersByRole(role);
	}

	@ApiOperation(value = "get companys by name")
	@GetMapping("/retrieve-companys-by-name/{name}")
	@ResponseBody
	public List<User> getCompanysWithName(@PathVariable("name") String name) {
		return userService.getCompanysByName(name);
	}

	@ApiOperation(value = "get employes by name")
	@GetMapping("/retrieve-employes-by-name/{name}")
	@ResponseBody
	public List<User> getEmployesWithName(@PathVariable("name") String name) {
		return userService.findEmployesByName(name);
	}

	@ApiOperation(value = "get employes by profession")
	@GetMapping("/retrieve-employes-by-profession/{idProfession}")
	@ResponseBody
	public List<User> getEmployesWithProfession(@PathVariable("idProfession") Long idProfession) {
		return userService.findEmployesWithProfession(idProfession);
	}

	@ApiOperation(value = "get companys by domain")
	@GetMapping("/retrieve-companys-by-domain/{idDomain}")
	@ResponseBody
	public List<User> getCompanysWithDomain(@PathVariable("idDomain") Long idDomain) {
		return userService.findCompanysWithDomain(idDomain);
	}

	@ApiOperation(value = "get companys by birthDate")
	@GetMapping("/retrieve-employes-by-birthDate/{startDate}/{endDate}")
	@ResponseBody
	public List<User> getEmployesWithBirthDate(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDebut = dateFormat.parse(startDate);
		Date dateFin = dateFormat.parse(endDate);
		return userService.findEmployesWithBirthDate(dateDebut, dateFin);
	}
}
