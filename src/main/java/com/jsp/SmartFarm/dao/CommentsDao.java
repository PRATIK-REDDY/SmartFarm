package com.jsp.SmartFarm.dao;

import java.util.Iterator;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.SmartFarm.entity.Comments;
import com.jsp.SmartFarm.entity.Post;
import com.jsp.SmartFarm.repo.CommentsRepo;
import com.jsp.SmartFarm.repo.PostRepo;

@Repository
public class CommentsDao {
	
	@Autowired
	private CommentsRepo commentsRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private PostDao postDao;
	
//	save
	public Comments saveComment(Comments comments) {
		return commentsRepo.save(comments);
	}
	
//	delete
	public Comments deleteComment(int Id) {
	    java.util.List<Post> posts = postRepo.findAll();
	    for (Post post : posts) {
	        java.util.List<Comments> comments = post.getComments();
	        Iterator<Comments> iterator = comments.iterator();
	        while (iterator.hasNext()) {
	            Comments comment = iterator.next();
	            if (comment.getId() == Id) {
	               iterator.remove();
	                postDao.updatePostDao(post);
	                comment.setUser(null);
	                commentsRepo.deleteById(Id);
	                return comment;
	            }
	        }
	    }
	    return null;
	}
}
