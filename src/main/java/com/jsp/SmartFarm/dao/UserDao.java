package com.jsp.SmartFarm.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo userRepo;
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	save
	public User saveDao(User movie) {
		return userRepo.save(movie);
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	fetch
	public User fetchDao(int id) {
		Optional<User> db = userRepo.findById(id);
		
		if (db.isPresent()) {
			return db.get();
		} else {
			return null;
		}
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	delete
	public User deleteDao(int id) {
		 Optional<User> db = userRepo.findById(id);
		
		if (db.isPresent()) {
			userRepo.deleteById(id);
			return db.get();
		} else {
			return null;
		}
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	update
	
	public User updateDao(User user) {
		Optional<User> db = userRepo.findById(user.getId());
		User m = db.get();
		if (db.isPresent()) {
			
			if (user.getFirstName() == null) {
				user.setFirstName(m.getFirstName());
			}
			if (user.getLastName() == null) {
				user.setLastName(m.getLastName());
			}
			if (user.getEmail() == null) {
				user.setEmail(m.getEmail());
			}
			if (user.getPhone() == 0) {
				user.setPhone(m.getPhone());
			}
			if (user.getPwd() == null) {
				user.setPwd(m.getPwd());
			}
			if (user.getGender() == null) {
				user.setGender(m.getGender());
			}
			if (user.getAge() == 0) {
				user.setAge(m.getAge());
			}
			if (user.getUserType() == null) {
				user.setUserType(m.getUserType());
			}
			if (user.getAddress() == null) {
				user.setAddress(m.getAddress());
			}
			
			userRepo.save(user);
			return db.get();
		} else {
			return null;
		}
	}
	
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public User fetchUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
