package com.jsp.SmartFarm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.SmartFarm.util.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandle {

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFound userNotFound) {
		
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("not found for User id");
		m.setMessage(userNotFound.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(EmailNotSendException.class)
	public ResponseEntity<ResponseStructure<String>> emailNotSend(EmailNotSendException emailNotSendException) {
		
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Email not send");
		m.setMessage(emailNotSendException.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	public ResponseEntity<ResponseStructure<String>> emailAlreadyRegistered(EmailAlreadyRegisteredException emailAlreadyRegistered) {
		
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Email Already Registered with this id");
		m.setMessage(emailAlreadyRegistered.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<ResponseStructure<String>> passwordMismatch(PasswordMismatchException passwordMismatch) {
		
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Password is Mismatch");
		m.setMessage(passwordMismatch.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
		
	}
}
