package com.continenantal.heat.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserException(UserNotFoundException ex, WebRequest request) throws Exception {
		
		ExcpetionResponse resp = new ExcpetionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(resp , HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExcpetionResponse resp = new ExcpetionResponse(new Date(),ex.getMessage(), ex.getBindingResult().toString());
		return new ResponseEntity<Object>(resp , HttpStatus.BAD_REQUEST);
	}


}
