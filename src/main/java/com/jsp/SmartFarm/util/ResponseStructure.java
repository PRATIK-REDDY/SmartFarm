package com.jsp.SmartFarm.util;

import java.util.List;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private String message;
	private int status;
	private T data;
	private List<T> listdata;
}
