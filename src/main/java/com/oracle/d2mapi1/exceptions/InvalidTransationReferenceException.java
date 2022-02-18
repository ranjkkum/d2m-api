package com.oracle.d2mapi1.exceptions;

public class InvalidTransationReferenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidTransationReferenceException(String errorMessage) {
		super(errorMessage);
	}

}