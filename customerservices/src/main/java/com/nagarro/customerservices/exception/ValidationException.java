package com.nagarro.customerservices.exception;
@SuppressWarnings("serial")

public class ValidationException extends RuntimeException{
	
	public ValidationException(String message) {
        super(message);
    }

}
