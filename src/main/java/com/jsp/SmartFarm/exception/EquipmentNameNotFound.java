package com.jsp.SmartFarm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentNameNotFound extends RuntimeException {
	
	private String message = "Equipment Name not found";
}
