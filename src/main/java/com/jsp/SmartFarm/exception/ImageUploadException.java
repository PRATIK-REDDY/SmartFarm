package com.jsp.SmartFarm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadException extends RuntimeException {

	private String message = "Can't be Uploaded";
}
