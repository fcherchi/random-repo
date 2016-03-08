
package com.lunatech.airports.controllers.exceptions;

/**
 * @author Fernando
 */

public class NoResultsException extends RuntimeException {

	/** serializaiton token	 */
	private static final long serialVersionUID = 5193588439859988669L;
	
	
	public NoResultsException(String message) {
		super(message);
	}
	
	public String message() {
		return getMessage();
	}
}
