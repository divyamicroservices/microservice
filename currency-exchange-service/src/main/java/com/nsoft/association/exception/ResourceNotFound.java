package com.nsoft.association.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFound extends RuntimeException{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFound(String message) {
        super(message);
    }

    public ResourceNotFound(String message, Throwable throwable) {
        super(message, throwable);
    }

}
