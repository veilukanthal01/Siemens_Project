package com.veilu.sprinboot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrganizationAlredayExistsException extends RuntimeException {
	public String message;
	
	public OrganizationAlredayExistsException() {
		
	}
	public OrganizationAlredayExistsException(String message) {
		 super(message);
		 this.message = message;
	}

}
