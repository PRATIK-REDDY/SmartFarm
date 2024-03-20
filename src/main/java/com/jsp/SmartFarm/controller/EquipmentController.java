package com.jsp.SmartFarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.SmartFarm.entity.Equipment;
import com.jsp.SmartFarm.service.EquipmentService;
import com.jsp.SmartFarm.util.ResponseStructure;

@RestController
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;
	
	@PostMapping("/saveEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> saveEquipment(@RequestParam int id, @RequestBody Equipment equipment) {
		return equipmentService.saveEquipment(id, equipment);
	}
	
	@GetMapping("/fetchByIdEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentById(@RequestParam int id) {
		return equipmentService.fetchByIdEquipment(id);
	}
	
	@GetMapping("/fetchAllEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentAll() {
		return equipmentService.fetchAllEquipment();
	}
	
	@GetMapping("/fetchByNameEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> fetchEquipmentByName(@RequestParam String name) {
		return equipmentService.fetchByName(name);
	}
	
	@GetMapping("/fetchByuserId")
	public ResponseEntity<ResponseStructure<List<Equipment>>> fetchByUserId(@RequestParam int user_id){
		return equipmentService.fetcByUserId(user_id);
	}
	
	@PutMapping("/updateEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> updateEquipment(@RequestBody Equipment equipment){
		return equipmentService.updateEquipmentService(equipment);
	}
	
	@DeleteMapping("/deleteEquipment")
	public ResponseEntity<ResponseStructure<Equipment>> deleteEquipment(@RequestParam int id){
		return equipmentService.deleteEquipmentService(id);
	}
}
