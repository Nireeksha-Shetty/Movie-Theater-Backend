package com.theatre.exception;

import lombok.Data;

@Data
public class TheaterAlreadyExistException extends Exception {

	String Code;
	public TheaterAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TheaterAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TheaterAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TheaterAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TheaterAlreadyExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public TheaterAlreadyExistException(String message, String Code) {
		super(message);
		this.Code = Code;
	}
}
