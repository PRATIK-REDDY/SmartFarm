package com.jsp.SmartFarm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.SmartFarm.entity.PaymentHistory;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Integer> {

}
