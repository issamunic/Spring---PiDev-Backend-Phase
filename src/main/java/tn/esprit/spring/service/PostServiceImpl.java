package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Community;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.repository.CommunityRepository;
import tn.esprit.spring.repository.PostRepository;
import tn.esprit.spring.serviceInterface.IPostService;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {
	
	@Autowired
	PostRepository postrepo;
	@Autowired
	CommunityRepository communityRepo;
	
	@Override
	public Post addPost(Post p) {
		// TODO Auto-generated method stub
		log.info("adding post");
		return postrepo.save(p);
	}

	@Override
	public void deletePost(Long id) {
		// TODO Auto-generated method stub
		postrepo.deleteById(id);
		
	}

	@Override
	public Post updatePost(Post p) {
		// TODO Auto-generated method stub
		return postrepo.save(p);
	}

	@Override
	public Post retrievePost(Long id) {
		return postrepo.findById(id).orElse(null);
	}

	@Override
	public List<Post> retrievePostsByCountry(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> retrievePostsByLocation(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> retrievePostsByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> retreivePostsByCommunity(Long idCommunity) {
		// TODO Auto-generated method stub
		Community community = communityRepo.findById(idCommunity).orElse(null);
		return postrepo.findAllPostsByCommunity(community);
	}
	

}
