package com.jsp.SmartFarm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.SmartFarm.repo.CommentsRepo;

@Repository
public class CommentsDao {
	
	@Autowired
	private CommentsRepo commentsRepo;
}
