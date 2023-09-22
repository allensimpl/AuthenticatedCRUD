package com.simplogics.baseapplication.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustException extends Exception {

	private static final long serialVersionUID = 7344098970658362381L;

	String message;

	private int messageCode;

	private String causeMessage = "";

	public CustException() {
		super();
	}

	public CustException(String message) {
		super(message);
		this.message = message;
	}

	public CustException(String message, int messageCode) {
		super(message);
		this.message = message;
		this.messageCode = messageCode;
	}

	public CustException(String message, int messageCode, String causeMessage) {
		super(message);
		this.message = message;
		this.messageCode = messageCode;
		this.causeMessage = causeMessage;
	}
}
