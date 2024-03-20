package com.jsp.SmartFarm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.SmartFarm.dao.CommentsDao;
import com.jsp.SmartFarm.dao.PostDao;
import com.jsp.SmartFarm.dao.UserDao;
import com.jsp.SmartFarm.entity.Comments;
import com.jsp.SmartFarm.entity.Post;
import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.exception.UserIdNotFound;
import com.jsp.SmartFarm.exception.UserNotFound;
import com.jsp.SmartFarm.repo.PostRepo;
import com.jsp.SmartFarm.util.ResponseStructure;

@Service
public class CommentsService {

	@Autowired
	private CommentsDao commentsDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private PostRepo postRepo;
	
	public ResponseEntity<ResponseStructure<Comments>> saveCommentsService(int postId, int UserId, String comments) {
		
		Optional<Post> postDb = postRepo.findById(postId);
		
		if (postDb != null) {
			User userDB = userDao.fetchDao(UserId);
			if (userDB != null) {
				Comments cm = new Comments();
				cm.setComment(comments);
				cm.setUser(userDB);
				
				Comments comments1 = commentsDao.saveComment(cm);
				Post p = postDb.get();
				List<Comments> postDb1 = new ArrayList<Comments>();
				postDb1.add(comments1);
				postDb1.addAll(p.getComments());
				
				p.setComments(postDb1);
				postDao.updatePostDao(p);
				ResponseStructure<Comments> m = new ResponseStructure<Comments>();
				m.setData(comments1);
				m.setMessage("comment posted successfully");
				m.setStatus(HttpStatus.CREATED.value());
				
				return new ResponseEntity<ResponseStructure<Comments>>(m, HttpStatus.CREATED);
			} else {
				throw new UserNotFound();
			}
		} else {
			throw new UserIdNotFound();
		}
	}
	
//	delete the data
	
	public ResponseEntity<ResponseStructure<Comments>> deleteComment(int commentId){
		Comments db = commentsDao.deleteComment(commentId);
		if(db!=null) {
			ResponseStructure<Comments> r= new ResponseStructure<Comments>();
			r.setData(db);
			r.setMessage("Message Delete Successfully");
			r.setStatus(HttpStatus.GONE.value());
			return new ResponseEntity<ResponseStructure<Comments>>(r,HttpStatus.GONE);
		}
		else {
			throw new UserIdNotFound();
		}
	}
}
