package com.jsp.SmartFarm.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.SmartFarm.dao.PostDao;
import com.jsp.SmartFarm.dao.UserDao;
import com.jsp.SmartFarm.entity.Image;
import com.jsp.SmartFarm.entity.Post;
import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.exception.ImageUploadException;
import com.jsp.SmartFarm.exception.PostIdNotFound;
import com.jsp.SmartFarm.exception.UserNotFound;
import com.jsp.SmartFarm.util.ResponseStructure;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Post>> savePostService(int userId, MultipartFile file, String caption, String location) {
		
		ResponseStructure<Post> m = new ResponseStructure<Post>();
		
		User db = userDao.fetchDao(userId);
		
		if (db != null) {
			
			Post post = new Post();
			Image image = new Image();
			image.setName(file.getOriginalFilename());
			try {
				image.setData(file.getBytes());
			} catch (IOException e) {
				throw new ImageUploadException();
			}
			post.setImgage(image);
			post.setCaption(caption);
			post.setLocation(location);
			post.setDateTime(LocalDateTime.now());			
			List<Post> p = new ArrayList<Post>();
			p.add(post);
			p.addAll(db.getPost());
			
			db.setPost(p);
			
			userDao.updateDao(db);
			m.setData(post);
			m.setMessage("Post Uploaded Successfully...");
			m.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Post>>(m, HttpStatus.OK);
		} else {
			throw new UserNotFound();
		}
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public ResponseEntity<ResponseStructure<Post>> fetchPostService(int id) {
		ResponseStructure<Post> m = new ResponseStructure<Post>(); 
		
		Post db = postDao.fetchPostDao(id);
		if (db != null) {
			m.setData(db);
			m.setMessage("fetch Post Successfully...");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Post>>(m, HttpStatus.FOUND);
		} else {
			throw new UserNotFound();
		}
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public ResponseEntity<ResponseStructure<Post>> deletePostService(int id) {
		
		ResponseStructure<Post> m = new ResponseStructure<Post>();
		Post post = postDao.deletePostDao(id);
		
		if (post != null) {
			m.setData(post);
			m.setMessage("Post is Deleted Successfully...");
			m.setStatus(HttpStatus.GONE.value());
			
			return new ResponseEntity<ResponseStructure<Post>>(m, HttpStatus.GONE);
		} else {
			throw new PostIdNotFound();
		}
		
		
	}
}
