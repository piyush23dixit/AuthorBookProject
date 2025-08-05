package com.example.demo.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthorBookAdvice {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException rnfe){
		return new ResponseEntity<String>("Not Found -> "+rnfe.getMessage(),HttpStatus.NOT_FOUND);
	}

}
