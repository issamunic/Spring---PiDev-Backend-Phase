package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.entities.Community;
import tn.esprit.spring.entities.Post;

public interface IPostService {
	
	Post addPost(Post p);
	
	void deletePost(Long id);
	
	Post updatePost(Post p);
	
	Post retrievePost(Long id);

	List<Post> retreivePostsByCommunity(Long idCommunity);
	
	List<Post> retrievePostsByCountry (String country);
	
	List<Post> retrievePostsByLocation (String location);
	
	List<Post> retrievePostsByCity (String city);
	
	
}
