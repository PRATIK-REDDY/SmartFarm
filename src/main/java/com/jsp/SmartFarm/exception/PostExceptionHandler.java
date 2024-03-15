package com.jsp.SmartFarm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.SmartFarm.util.ResponseStructure;

@RestControllerAdvice
public class PostExceptionHandler {

	@ExceptionHandler(PostIdNotFound.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(PostIdNotFound postIdNotFound) {

		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("not found for Post id");
		m.setMessage(postIdNotFound.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
	}
}
