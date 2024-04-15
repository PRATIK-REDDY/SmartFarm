package com.jsp.SmartFarm.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.SmartFarm.dao.EquipmentDao;
import com.jsp.SmartFarm.dao.PaymentHistoryDao;
import com.jsp.SmartFarm.dao.RentalDao;
import com.jsp.SmartFarm.entity.Equipment;
import com.jsp.SmartFarm.entity.PaymentHistory;
import com.jsp.SmartFarm.entity.Rental;
import com.jsp.SmartFarm.exception.EquipmentNameNotFound;
import com.jsp.SmartFarm.util.ResponseStructure;

@Service
public class RentalService {

	@Autowired
	private RentalDao rentalDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private PaymentHistoryDao paymentHistoryDao;
	
	public ResponseEntity<ResponseStructure<Rental>> saveRentalService(int equipmentId, Rental rental) {
		Equipment equipment = equipmentDao.fetchByIdEquipment(equipmentId);
		
		ResponseStructure<Rental> r = new ResponseStructure<Rental>();
		LocalDateTime startTime = rental.getStartTime();
		LocalDateTime enDateTime = rental.getEndTime();
		
		if (startTime.isAfter(enDateTime)) {
			throw new IllegalArgumentException("Rental Start Time Cannot be after End Time");
		}
		
		if (equipment != null) {
			Duration duration = Duration.between(startTime, enDateTime);
			long hours = duration.toHours();
			double amount = equipment.getCostperhours() * hours;
			PaymentHistory payment = new PaymentHistory();
			
			payment.setAmount(amount);
			paymentHistoryDao.savePaymentDao(payment);
			
			r.setData(rentalDao.saveDao(rental));
			r.setMessage("Rental Details Saved Successfully.");
			r.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<Rental>>(r, HttpStatus.OK);
		} else {
			throw new EquipmentNameNotFound();
		}
	}
}
