package com.jsp.SmartFarm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.SmartFarm.entity.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {

}
