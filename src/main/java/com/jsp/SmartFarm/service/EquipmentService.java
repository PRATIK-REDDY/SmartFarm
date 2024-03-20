package com.jsp.SmartFarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.SmartFarm.dao.EquipmentDao;
import com.jsp.SmartFarm.dao.UserDao;
import com.jsp.SmartFarm.entity.Equipment;
import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.exception.EquipmentNameNotFound;
import com.jsp.SmartFarm.exception.UserIdNotFound;
import com.jsp.SmartFarm.util.ResponseStructure;

@Service
public class EquipmentService {

	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private UserDao userDao;
	
//	save
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(int u_id,Equipment equipment) {
		User db = userDao.fetchDao(u_id);
		ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
		if(db!=null) {
			equipment.setUser(db);
			equipmentDao.saveEquipment(equipment);
			m.setData(equipment);
			m.setMessage("equipment add Successfully");
			m.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserIdNotFound();
		}
	}
	
//	findbyId
	public ResponseEntity<ResponseStructure<Equipment>> fetchByIdEquipment(int id){
		Equipment db = equipmentDao.fetchByIdEquipment(id);
		if(db!=null) {
			ResponseStructure<Equipment> m =new ResponseStructure<Equipment>();
			m.setData(db);
			m.setMessage("fetch successfully");
			m.setStatus(HttpStatus.FOUND.value());
			 return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			return null;
		}
	}
	
//	fethAll
	public ResponseEntity<ResponseStructure<Equipment>> fetchAllEquipment(){
		List<Equipment> db = equipmentDao.fetchAllEquipment();
		ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
		if(db!=null) {
			m.setListdata(db);
			m.setMessage("Fetch All Equipment successfully");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserIdNotFound();
		}
	}
	
//	fetchByname
	public ResponseEntity<ResponseStructure<Equipment>> fetchByName(String equipmentname){
		Equipment db = equipmentDao.fetchByName(equipmentname);
		ResponseStructure<Equipment> m = new ResponseStructure<>();
		if(db!=null) {
			m.setData(db);
			m.setMessage("Fetch Equiment by name successfully");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.FOUND);
		}
		else {
			throw new EquipmentNameNotFound();
		}
	}
	
//	fetchByuserId
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetcByUserId(int user_id){
		User db = userDao.fetchDao(user_id);
		ResponseStructure<List<Equipment>> m = new ResponseStructure<>();
		if(db!=null) {
			List<Equipment> db1 = equipmentDao.fetchByUserId(db);
			m.setData(db1);
			m.setMessage("Fetch Equipment by User_id Successfully");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Equipment>>>(m,HttpStatus.FOUND);
		}
		else {
			throw new UserIdNotFound();
		}
	}
	
//	update 
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipmentService(Equipment equipment){
		Equipment db = equipmentDao.updateEquipment(equipment);
		ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
		if(db!=null) {
			m.setData(db);
			m.setMessage("Update Equipment Successfully");
			m.setStatus(HttpStatus.CREATED.value());
			 return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.CREATED);
		}
		else {
			throw new UserIdNotFound();
		}
	}
	
//	delete
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipmentService(int id){
		Equipment db = equipmentDao.deleteEquipment(id);
		ResponseStructure<Equipment> m = new ResponseStructure<Equipment>();
		if(db!=null) {
			m.setData(db);
			m.setMessage("Delete Equipment Successfully");
			m.setStatus(HttpStatus.GONE.value());
			
			return new ResponseEntity<ResponseStructure<Equipment>>(m,HttpStatus.GONE);
		}
		else {
			throw new UserIdNotFound();
		}
	}
}
