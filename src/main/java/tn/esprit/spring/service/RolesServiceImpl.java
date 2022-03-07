package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Roles;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.RolesRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.serviceInterface.IRolesService;

@Service
@Slf4j
public class RolesServiceImpl implements IRolesService{
	
	@Autowired
	RolesRepository rolesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Roles> retrieveAllRoles() {
		List<Roles> roless=rolesRepository.findAll();
		for(Roles roles:roless) {
			log.info("roles : "+roles);
		}
		return roless;
	}

	@Override
	public Roles addRoles(Roles roles) {
		try {
			Roles r=rolesRepository.save(roles);
			log.info("roles added : "+r);
			return r;
		}
		catch(Exception e) {
			log.info("erreur roles add : "+e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteRoles(Long id) {
		try {
			rolesRepository.deleteById(id);
			log.info("roles deleted with id: "+id);
		}
		catch(Exception e) {
			log.info("erreur roles delete : "+id);
		}
	}

	@Override
	public Roles updateRoles(Roles roles) {
		try {
			Roles rolesUpdated=rolesRepository.save(roles);
			List<User> users=userRepository.findAll();
			for(User u:users) {
				if(u.getRoles().size()>0) {
					for(Roles r:u.getRoles()) {
						if(r.getIdRoles()==roles.getIdRoles()) {
							u.setRole(Role.valueOf(rolesUpdated.getRoleName()));
							userRepository.save(u);
						}
					}
				}
			}
			log.info("roles update : "+rolesUpdated);
			return rolesUpdated;
		}
		catch(Exception e) {
			log.info("erreur roles update : "+e.getMessage());
			return null;
		}
	}

	@Override
	public Roles retrieveRoles(Long id) {
		try {
			Roles r=rolesRepository.findById(id).orElse(null);
			log.info("roles retrieve : "+r);
			return r;
		}
		catch(Exception e) {
			log.info("erreur roles retrieve : "+e.getMessage());
			return null;
		}
	}

}

