package com.veilu.sprinboot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AssetAlredayExistsException extends RuntimeException {
	public String message;
	
	public AssetAlredayExistsException() {
		
	}
	public AssetAlredayExistsException(String message) {
		 super(message);
		 this.message = message;
	}

}
