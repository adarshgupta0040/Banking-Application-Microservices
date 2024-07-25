package com.nagarro.accountservices.exceptions;

@SuppressWarnings("serial")
public class ValidationException extends RuntimeException{
	
	public ValidationException(String message) {
        super(message);
    }

}
