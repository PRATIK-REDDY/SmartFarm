package com.jsp.SmartFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.SmartFarm.entity.Post;
import com.jsp.SmartFarm.service.PostService;
import com.jsp.SmartFarm.util.ResponseStructure;

@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/postUpload")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam int id,@RequestParam MultipartFile file,@RequestParam String caption,@RequestParam String location) {
		return postService.savePostService(id, file, caption, location);
	}
	
	@GetMapping("/postFetch")
	public ResponseEntity<ResponseStructure<Post>> fetchPost(@RequestParam int id) {
		return postService.fetchPostService(id);
	}
	
	@DeleteMapping("/postDelete")
	public ResponseEntity<ResponseStructure<Post>> deletePost(@RequestParam int id) {
		return postService.deletePostService(id);
	}
}
