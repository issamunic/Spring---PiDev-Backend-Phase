package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.serviceInterface.ICommentService;

public class CommentServiceImpl implements ICommentService {

	@Override
	public Comment addComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment addSentimentToComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> retrieveCommentsByPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> retrieveCommentsByPostAndSentiment(Post post, String sentiment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> sentimentsOfCommentsByPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

}
