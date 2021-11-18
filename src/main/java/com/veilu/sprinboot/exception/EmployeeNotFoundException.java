package com.veilu.sprinboot.exception;

public class EmployeeNotFoundException  extends RuntimeException {
	public String message;
	
public EmployeeNotFoundException() {
		
	}
	public EmployeeNotFoundException(String message) {
		 super(message);
		 this.message = message;
	}

}
