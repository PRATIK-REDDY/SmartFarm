package com.jsp.SmartFarm.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum UserType {
	
	@Enumerated(EnumType.STRING)
	FARMERS,
	@Enumerated(EnumType.STRING)
	EXPERTS;
}
