package com.example.model;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.controller.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

@ExceptionHandler(Exception.class)
protected ResponseEntity<Object> handleAllException(Exception ex,WebRequest request) {
	
	// TODO Auto-generated method stub
	ExceptionResponse exceptionResp=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
	return new ResponseEntity<Object>(exceptionResp,HttpStatus.INTERNAL_SERVER_ERROR);
}
@ExceptionHandler(UserNotFoundException.class)
protected ResponseEntity<Object> handleNotFoundException(UserNotFoundException ex,WebRequest request) {
	
	// TODO Auto-generated method stub
	ExceptionResponse exceptionResp=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
	return new ResponseEntity<Object>(exceptionResp,HttpStatus.NOT_FOUND);
}
}
