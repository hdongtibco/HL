package com.tibco.devtools.builder;

public class UnsatisfiedDependencyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsatisfiedDependencyException(String message) {
		super(message);
	}

	public UnsatisfiedDependencyException(Throwable cause) {
		super(cause);
	}

	public UnsatisfiedDependencyException(String message, Throwable cause) {
		super(message, cause);
	}

}
