package com.cart.cart.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException ex, WebRequest request){
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
		
	}

}
