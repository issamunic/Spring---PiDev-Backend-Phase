package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Community;
import tn.esprit.spring.entities.User;

public interface ICommunityService {
	
	Community addCommunity(Community community);
	
	Community updateCommunity(Community community);
	
	void deleteCommunity(Long id);
	
	Community retriveCommunity(Long id);
	
	List<Community> retrieveCommunitiesByLocation(String location);
	
	List<Community> retrieveCommunitiesByCity(String city);
	
	List<Community> retrieveCommunitiesByUser(User user);
	
	

}
