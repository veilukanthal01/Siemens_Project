package com.veilu.sprinboot.exception;

public class AssetNotFoundException  extends RuntimeException {
	public String message;
	
public AssetNotFoundException() {
		
	}
	public AssetNotFoundException(String message) {
		 super(message);
		 this.message = message;
	}

}
