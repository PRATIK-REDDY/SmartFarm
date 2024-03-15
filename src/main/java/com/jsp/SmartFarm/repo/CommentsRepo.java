package com.jsp.SmartFarm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.SmartFarm.entity.Comments;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {

}
