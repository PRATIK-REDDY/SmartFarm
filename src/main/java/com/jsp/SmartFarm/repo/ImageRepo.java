package com.jsp.SmartFarm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.SmartFarm.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {

}
