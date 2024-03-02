package com.jsp.SmartFarm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.SmartFarm.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

//	here you write the customer Query
	
	@Query("Select a from User a where email=?1")
	public User findByEmail(String email);
}
