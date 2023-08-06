package com.seerbit.transaction.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException() {
		super("bad request");
	}

}
