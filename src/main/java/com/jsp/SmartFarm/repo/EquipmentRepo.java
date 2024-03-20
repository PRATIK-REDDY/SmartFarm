package com.jsp.SmartFarm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.SmartFarm.entity.Equipment;
import com.jsp.SmartFarm.entity.User;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {
	
	@Query("select a from Equipment a where a.name=:name")
	Equipment fetchByname(String name);
	
	@Query("select a from  Equipment a  where a.user=?1")
	List<Equipment> fetchByuserId(User user);
}
