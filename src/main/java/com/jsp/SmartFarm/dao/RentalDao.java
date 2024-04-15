package com.jsp.SmartFarm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.SmartFarm.entity.Rental;
import com.jsp.SmartFarm.repo.RentalRepo;

@Repository
public class RentalDao {

	@Autowired
	private RentalRepo rentalRepo;
	
	public Rental saveDao(Rental rental) {
		return rentalRepo.save(rental);
	}
	
}
