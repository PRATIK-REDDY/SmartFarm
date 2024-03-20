package com.jsp.SmartFarm.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.SmartFarm.entity.Image;
import com.jsp.SmartFarm.entity.Post;
import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.repo.PostRepo;
import com.jsp.SmartFarm.repo.UserRepo;

@Repository
public class PostDao {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ImageDao imageDao;
	
//	save Post
	public Post saveDao(Post post) {
		return postRepo.save(post);
	}
	
//	fetch Post
	public Post fetchPostDao(int id) {
		Optional<Post> db = postRepo.findById(id);

		if (db.isPresent()) {
			return db.get();
		} else {
			return null;
		}
	}
	
//	delete Post
	public Post deletePostDao(int id) {
		List<User> users = userRepo.findAll();
		
		for (User user : users) {
			List<Post> posts = user.getPost();
			Iterator<Post> iterator = posts.iterator();
			while (iterator.hasNext()) {
				Post post = iterator.next();
				
				if (post.getId() == id) {
					iterator.remove();
					userDao.updateDao(user);
					postRepo.deleteById(id);
					imageDao.deleteDao(id);
					return post;
				}
			}
		}
		return null;
	}
	
//	update Post
	public Post updatePostDao(Post post) {
		Optional<Post> db = postRepo.findById(post.getId());
		
		if (db.isPresent()) {
			Post data = db.get();
			
			if (post.getComments() == null) {
				post.setComments(data.getComments());
			}
			if(post.getCaption()==null) {
				post.setCaption(data.getCaption());
			}
			if(post.getLocation()==null) {
				post.setLocation(data.getLocation());
			}
			if(post.getDateTime()==null) {
				post.setDateTime(data.getDateTime());
			}
			
			return postRepo.save(data);
		} else {
			return null;
		}
	}
}
