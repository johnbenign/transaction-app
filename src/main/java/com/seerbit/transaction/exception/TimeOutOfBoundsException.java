package com.seerbit.transaction.exception;

public class TimeOutOfBoundsException extends RuntimeException {

	public TimeOutOfBoundsException(String message) {
		super(message);
	}

	public TimeOutOfBoundsException() {
		super("Time exceeds the given duration");
	}

}
