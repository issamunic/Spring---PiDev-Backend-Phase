package tn.esprit.spring.security;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//return new User("med","br",new ArrayList<>());
		tn.esprit.spring.entities.User userCurrent=userRepo.findByLogin(username);
		if(userCurrent!=null) {
			return new User(userCurrent.getLogin(),userCurrent.getPassword(),new ArrayList<>());
		}
		return null;
	}

}
