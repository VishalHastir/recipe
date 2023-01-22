package com.abnamro.exam.recipe.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Vishal
 *
 */
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus status = HttpStatus.NOT_FOUND;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message) {
		super(message);
		this.status = status;
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
