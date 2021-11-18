package com.veilu.sprinboot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmployeeAlredayExistsException extends RuntimeException {
	public String message;
	
	public EmployeeAlredayExistsException() {
		
	}
	public EmployeeAlredayExistsException(String message) {
		 super(message);
		 this.message = message;
	}

}
