package com.lunatech.airports.services;

/**
 * Exception thrown by the service
 * @author Fernando
 *
 */
public class ServiceException extends RuntimeException{

	/** Serialization token */
	private static final long serialVersionUID = 1584890295244458798L;

	/**
	 * Creates an exception with inner
	 * @param message
	 * @param innerException
	 */
	public ServiceException(String message, Throwable innerException) {
		super(message, innerException);
	}
	
	/**
	 * Creates an exception with the message
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
}
