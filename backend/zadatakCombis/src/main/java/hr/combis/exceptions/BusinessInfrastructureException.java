package hr.combis.exceptions;

import lombok.Data;


public class BusinessInfrastructureException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessInfrastructureException(String message) {
		super(message);
	}

}
