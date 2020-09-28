package com.itinerarios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExceptionServiceGeneral  extends RuntimeException {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionServiceGeneral(String message) {
	    super(message);
	  }
	
}
