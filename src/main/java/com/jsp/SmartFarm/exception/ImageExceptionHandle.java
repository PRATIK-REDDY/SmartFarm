package com.jsp.SmartFarm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.SmartFarm.util.ResponseStructure;

@RestControllerAdvice
public class ImageExceptionHandle {
	
	@ExceptionHandler(ImageUploadException.class)
	public ResponseEntity<ResponseStructure<String>> userNotFound(ImageUploadException imageUploadException) {
		
		ResponseStructure<String> m = new ResponseStructure<String>();
		m.setData("Image can't be Uploaded");
		m.setMessage(imageUploadException.getMessage());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(m, HttpStatus.NOT_FOUND);
		
	}

}
