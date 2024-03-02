package com.jsp.SmartFarm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordMismatchException extends RuntimeException {
	
	private String message = "not found";
}
