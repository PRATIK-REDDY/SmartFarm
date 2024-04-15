package com.jsp.SmartFarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.SmartFarm.entity.Rental;
import com.jsp.SmartFarm.service.RentalService;
import com.jsp.SmartFarm.util.ResponseStructure;

@RestController
public class RentalController {

	@Autowired
	private RentalService rentalService;
	
	public ResponseEntity<ResponseStructure<Rental>> saveRental(int equipmentId, Rental rental) {
		return rentalService.saveRentalService(equipmentId, rental);
	}
}
