package com.seerbit.transaction.exception.handler;

import com.seerbit.transaction.exception.BadRequestException;
import javax.validation.ConstraintViolationException;

import com.seerbit.transaction.exception.TimeOutOfBoundsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		log.info("Method Argument not valid throwing....", ex);
		return ResponseEntity.unprocessableEntity().build();

	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		log.info("Throwing Illegal Argument Exception:::", ex);
		return ResponseEntity.badRequest().build();
	}

	@ExceptionHandler(value = { BadRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> badRequestExceptionHandler(BadRequestException ex) {
		log.info("Throwing bad request:: ", ex);
		return ResponseEntity.badRequest().build();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<Object> handleException(ConstraintViolationException ex) {
		log.error("Constraint Violation Exception :::", ex);
		return ResponseEntity.unprocessableEntity().build();
	}

	@ExceptionHandler(TimeOutOfBoundsException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> handleTimeOutOfBoundException(TimeOutOfBoundsException ex) {
		log.error("Throwing Time Out Of Bounds Exception:::", ex);
		return ResponseEntity.noContent().build();
	}
}
