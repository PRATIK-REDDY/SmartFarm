package com.jsp.SmartFarm.exception;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.mapping.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.SmartFarm.util.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class UserExceptionHandle extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFound userNotFound) {

		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("not found for User id");
		m.setMessage(userNotFound.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserIdNotFound.class)
	public ResponseEntity<ResponseStructure<String>> userIdNotFound(UserIdNotFound userIdNotFound) {

		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("not found for User id");
		m.setMessage(userIdNotFound.getMessage());
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
	public ResponseEntity<ResponseStructure<String>> emailAlreadyRegistered(
			EmailAlreadyRegisteredException emailAlreadyRegistered) {

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

//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		java.util.List<ObjectError> error = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		ResponseStructure<Object> structure = new ResponseStructure<>();

		for (ObjectError objectError : error) {
			String filedName = ((FieldError) objectError).getField();
			String message = ((FieldError) objectError).getDefaultMessage();
			map.put(filedName, message);
		}
		structure.setMessage("provide valid details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<Object>> handleConstraintViolationException(
			ConstraintViolationException ex) {
		ResponseStructure<Object> structure = new ResponseStructure();
		Map<String, String> map = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			String field = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			map.put(field, message);

		}

		structure.setMessage("provide proper details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<ResponseStructure<Object>>(structure, HttpStatus.BAD_REQUEST);

	}
}
