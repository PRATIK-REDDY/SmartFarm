package com.jsp.SmartFarm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserIdNotFound extends RuntimeException {

	private String message = "UserId not found";
}
