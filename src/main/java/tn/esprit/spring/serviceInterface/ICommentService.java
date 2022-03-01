package tn.esprit.spring.serviceInterface;

import java.util.List;

import tn.esprit.spring.dto.CommentsDto;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;

public interface ICommentService {
	
	Comment addComment(CommentsDto commentDto);
	
	String addSentimentToComment(Comment comment);
	
	void deleteComment(Long id);
	
	List<Comment> retrieveCommentsByPost(Long postId);
	
	List<Comment> retrieveCommentsByPostAndSentiment(Post post, String sentiment);
	
	List<String> sentimentsOfCommentsByPost(Long postId);

}
