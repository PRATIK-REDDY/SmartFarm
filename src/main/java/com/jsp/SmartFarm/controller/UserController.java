package com.jsp.SmartFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.service.UserService;
import com.jsp.SmartFarm.util.ResponseStructure;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	postMapping (for save)
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	getMapping (for login)
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String email, @RequestParam String password) {
		return userService.loginUser(email, password);
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	getMapping (for fetch)
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<User>> fetchUser(@RequestParam int id) {
		return userService.fetchUser(id);
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	deleteMapping (for delete)
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int id) {
		return userService.deleteUser(id);
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	putMapping (for update)
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	getMapping (for sendOTP)
	
	@GetMapping("/sendOtp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email) {
		return userService.sendOtp(email);
	}
}
