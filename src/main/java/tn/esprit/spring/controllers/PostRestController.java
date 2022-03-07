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
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.serviceInterface.IPostService;

@RestController
@Api(tags = "Posts Management")
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	IPostService postService;
	
	@ApiOperation(value = "get list posts in a community")
	@GetMapping("/retrieve-all-posts-community")
	@ResponseBody
	public List<Post> getPostsByCommunity(Long communityId) {
		return postService.retreivePostsByCommunity(communityId);
	}
	
	@ApiOperation(value = "retrieve post")
	@GetMapping("/retrieve-post/{postId}")
	@ResponseBody
	public Post getPost(@PathVariable("postId") Long id) {
		return postService.retrievePost(id);
	}
	@ApiOperation(value = "retrieve post by country")
	@GetMapping("/retrieve-post/bycountry/{country}")
	@ResponseBody
	public List<Post> getPostByCountry(@PathVariable String country) {
		return postService.retrievePostsByCountry(country);
	}
	@ApiOperation(value = "retrieve all posts")
	@GetMapping("/retrieve-posts")
	@ResponseBody
	public List<Post> getPosts() {
		return postService.retreiveAllPosts();
	}
	
	@ApiOperation(value = "Add Post")
	@PostMapping("/add-post")
	@ResponseBody
	public Post addUser(@RequestBody Post post){
		return postService.addPost(post);
	}
	
	@ApiOperation(value = "Delete Post")
	@DeleteMapping("/remove-post/{postid}")
	@ResponseBody
	public void deletePost(@PathVariable("postid") Long idPost){
		postService.deletePost(idPost);
	}
	
	@ApiOperation(value = "update post")
	@PutMapping("/modify-post")
	@ResponseBody
	public Post updateUser(@RequestBody Post post){
		return postService.updatePost(post);
	}

}
