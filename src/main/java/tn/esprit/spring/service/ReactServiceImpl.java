package tn.esprit.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.dto.ReactDto;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.React;
import static tn.esprit.spring.entities.ReactType.UpVote;

import tn.esprit.spring.repository.PostRepository;
import tn.esprit.spring.repository.ReactRepository;
import tn.esprit.spring.serviceInterface.IReactService;

@Service
@Slf4j
public class ReactServiceImpl implements IReactService {
	
	@Autowired 
	ReactRepository reactRepository;
	@Autowired
	PostRepository postRepository;
	

	@Override
	public void React(ReactDto reactDto) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(reactDto.getPostId()).orElse(null);
		if(post != null) {
			Optional<React> voteByPostandUser = reactRepository.findTopByPostAndUserOrderByIdReactDesc(post, null);
			if (voteByPostandUser.isPresent() && voteByPostandUser.get().getReactType().equals(reactDto.getReactType())) {
				log.warn("you have already " + reactDto.getReactType() +"'ed this post.");
			}
			if(UpVote.equals(reactDto.getReactType())) {
				post.setReactCount(post.getReactCount()+1);
			}
			else post.setReactCount(post.getReactCount() -1);
			
			reactRepository.save(mapToVote(reactDto, post));
			postRepository.save(post);
		}
		
	}
	private React mapToVote(ReactDto reactDto, Post post) {
        return React.builder()
                .reactType(reactDto.getReactType())
                .post(post)
                .user(null)
                .build();
    }

}
