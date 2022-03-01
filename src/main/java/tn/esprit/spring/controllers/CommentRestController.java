package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.dto.CommentsDto;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.serviceInterface.ICommentService;

@RestController
@Api(tags = "Gestion des comments")
@RequestMapping("/comment")
public class CommentRestController {
	
	@Autowired
	ICommentService comservice;
	
	@ApiOperation(value = "retrieve comments")
	@GetMapping("/retrieve-comment/{postId}")
	@ResponseBody
	public List<Comment> getComments(@PathVariable("postId") Long id) {
		return comservice.retrieveCommentsByPost(id);
	}
	
	
	@ApiOperation(value = "Add Comment")
	@PostMapping("/add-comment")
	@ResponseBody
	public Comment addComment(@RequestBody CommentsDto commentDto){
		return comservice.addComment(commentDto);
	}
	
	@ApiOperation(value = "Delete Comment")
	@DeleteMapping("/remove-comment/{commentid}")
	@ResponseBody
	public void deleteComment(@PathVariable("commentid") Long idComment){
		comservice.deleteComment(idComment);
	}
	
	

}
