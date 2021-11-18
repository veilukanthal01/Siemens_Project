package com.veilu.sprinboot.exception;

public class OrganizationNotFoundException  extends RuntimeException {
	public String message;
	
public OrganizationNotFoundException() {
		
	}
	public OrganizationNotFoundException(String message) {
		 super(message);
		 this.message = message;
	}

}
