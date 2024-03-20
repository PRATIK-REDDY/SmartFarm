package com.jsp.SmartFarm.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.SmartFarm.entity.Equipment;
import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.repo.EquipmentRepo;

@Service
public class EquipmentDao {
	
	@Autowired
	private EquipmentRepo equipmentRepo;
	
//	save
	public Equipment saveEquipment(Equipment equipment) {
		return equipmentRepo.save(equipment);
	}
	
//	fetchByid
	public Equipment fetchByIdEquipment(int id) {
		Optional<Equipment> db = equipmentRepo.findById(id);
		if(db!=null) {
			return db.get();
		}
		else {
			return null;
		}
	}
	
//	fetchAll
	public java.util.List<Equipment> fetchAllEquipment(){
		return equipmentRepo.findAll();
	}
	
//	fetchByname
	public Equipment fetchByName(String equipmentname) {
		Equipment db = equipmentRepo.fetchByname(equipmentname);
		if(db!=null) {
			return db;
		}
		else {
			return null;
		}
	}
	
//	fetchByUserId
	public java.util.List<Equipment> fetchByUserId(User user) {
		java.util.List<Equipment> db = equipmentRepo.fetchByuserId(user);
		if(db!=null) {
			return db;
		}
		else {
			return null;
		}
	}
	
//	update
	public Equipment updateEquipment(Equipment equipment){
		Optional<Equipment> db = equipmentRepo.findById(equipment.getId()); 
		if(db.isPresent()) {
			Equipment data = db.get();
			if(equipment.getName()==null) {
				equipment.setName(data.getName());
			}
			if(equipment.getQuantity()==0) {
				equipment.setQuantity(data.getQuantity());;
			}
			if(equipment.getCostperhours()==0) {
				equipment.setCostperhours(data.getCostperhours());;
			}
			if(equipment.getUser()==null) {
				equipment.setUser(data.getUser());;
			}
			return equipmentRepo.save(equipment);
		}
		else {
			return null;
		}
	}
	
//	delete
	public Equipment deleteEquipment(int id) {
		Optional<Equipment> db = equipmentRepo.findById(id);
		if(db.isPresent()) {
			Equipment equipment=new Equipment();
			equipment.setUser(null);
			updateEquipment(equipment);
			equipmentRepo.deleteById(id);
			return db.get();
		}
		else {
			return null;
		}
	}
}
