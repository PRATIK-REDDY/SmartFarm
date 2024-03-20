package com.jsp.SmartFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.SmartFarm.entity.Comments;
import com.jsp.SmartFarm.service.CommentsService;
import com.jsp.SmartFarm.util.ResponseStructure;

@RestController
public class CommentsController {

	@Autowired
	private CommentsService commentsService;
	
	@PostMapping("/saveComment")
	public ResponseEntity<ResponseStructure<Comments>> saveComments(@RequestParam int postId,@RequestParam int UserId,@RequestParam String comment) {
		return commentsService.saveCommentsService(postId, UserId, comment);
	}
	
	@DeleteMapping("/deleteComment")
	public ResponseEntity<ResponseStructure<Comments>> deleteComments(@RequestParam int commentId) {
		return commentsService.deleteComment(commentId);
	}
}
