package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IUserService;

@Service
@Slf4j
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> retrieveAllUsers() {
		List<User> users=userRepository.findAll();
		for(User user:users) {
			log.info("user : "+user);
		}
		return users;
	}

	@Override
	public User addUser(User user) {
		try {
			User u=userRepository.save(user);
			log.info("user added : "+u);
			return u;
		}
		catch(Exception e) {
			log.info("erreur user add : "+e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
			log.info("user deleted with id: "+id);
		}
		catch(Exception e) {
			log.info("erreur user delete : "+id);
		}
	}

	@Override
	public User updateUser(User user) {
		try {
			User u=userRepository.save(user);
			log.info("user update : "+u);
			return u;
		}
		catch(Exception e) {
			log.info("erreur user update : "+e.getMessage());
			return null;
		}
	}

	@Override
	public User retrieveUser(Long id) {
		try {
			User u=userRepository.findById(id).orElse(null);
			log.info("user retrieve : "+u);
			return u;
		}
		catch(Exception e) {
			log.info("erreur user retrieve : "+e.getMessage());
			return null;
		}
	}

}
