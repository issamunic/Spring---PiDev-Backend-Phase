package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Community;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CommunityRepository;
import tn.esprit.spring.serviceInterface.ICommunityService;

public class CommunityServiceImpl implements ICommunityService {

	@Autowired 
	CommunityRepository comRepo;
	@Override
	public Community addCommunity(Community community) {
		// TODO Auto-generated method stub
		return comRepo.save(community);
	}

	@Override
	public Community updateCommunity(Community community) {
		// TODO Auto-generated method stub
		return comRepo.save(community);
	}

	@Override
	public void deleteCommunity(Long id) {
		// TODO Auto-generated method stub
		comRepo.deleteById(id);
		
	}

	@Override
	public Community retriveCommunity(Long id) {
		// TODO Auto-generated method stub
		return comRepo.findById(id).orElse(null);
	}

	@Override
	public List<Community> retrieveCommunitiesByLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Community> retrieveCommunitiesByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Community> retrieveCommunitiesByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
