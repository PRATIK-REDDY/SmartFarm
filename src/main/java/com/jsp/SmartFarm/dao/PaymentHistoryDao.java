package com.jsp.SmartFarm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.SmartFarm.entity.PaymentHistory;
import com.jsp.SmartFarm.repo.PaymentHistoryRepo;

@Repository
public class PaymentHistoryDao {
	
	@Autowired
	private PaymentHistoryRepo paymentHistoryRepo;

	public PaymentHistory savePaymentDao(PaymentHistory paymentHistory) {
		return paymentHistoryRepo.save(paymentHistory);
	}
}
