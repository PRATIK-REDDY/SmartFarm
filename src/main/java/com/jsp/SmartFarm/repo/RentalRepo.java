package com.jsp.SmartFarm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.SmartFarm.entity.Rental;

public interface RentalRepo extends JpaRepository<Rental, Integer> {

}
