package com.theatre.exception;

public class TheaterNotFoundException extends Exception {
	String Code;
	public TheaterNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public TheaterNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TheaterNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TheaterNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TheaterNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public TheaterNotFoundException(String message,String code) {
		super(message);
		// TODO Auto-generated constructor stub
		this.Code = code;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

}

