package com.nitin.webservices.rest.exception;

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

import com.nitin.webservices.rest.UserNotfoundException;

//comman exception format and 
@ControllerAdvice   //it applied to all controllers
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	//Generic exception for all request
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
    
		ExceptionResponse exceptionResponse	=
				new ExceptionResponse(new Date(),ex.getMessage(),
				request.getDescription(false));
	
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//for user not found we can write this as below 
	
	@ExceptionHandler(UserNotfoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotfoundException ex, WebRequest request) throws Exception {
    
		ExceptionResponse exceptionResponse	=
				new ExceptionResponse(new Date(),"Hey validation failed..",
				request.getDescription(false));
	
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse	=
				new ExceptionResponse(new Date(),ex.getMessage(),
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	
}
