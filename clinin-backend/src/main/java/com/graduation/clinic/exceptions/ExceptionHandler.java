package com.graduation.clinic.exceptions;



import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.validation.ConstraintViolationException;


@ControllerAdvice
public class ExceptionHandler {
	
	
	 @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        List<String> errors = ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(error -> error.getField() + " " + error.getDefaultMessage())
	            .collect(Collectors.toList());

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("status", HttpStatus.BAD_REQUEST.value());
	        body.put("errors", errors);

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }

	    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex) {
	        List<String> errors = ex.getConstraintViolations()
	            .stream()
	            .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
	            .collect(Collectors.toList());

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("status", HttpStatus.BAD_REQUEST.value());
	        body.put("errors", errors);

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	    
	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException NFEX){
		ErrorResponse error= new ErrorResponse(NFEX.getMessage());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(error);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(DuplicateException.class)
	public ResponseEntity<?> handleDuplicateException(DuplicateException DEX){
		ErrorResponse error=new ErrorResponse(DEX.getMessage());
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(error);
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(TimeException.class)
	public ResponseEntity<?> handleTimeException(TimeException TEX){
		ErrorResponse error=new ErrorResponse(TEX.getMessage());
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(error);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(GenericException.class)
	public ResponseEntity<?> handelanyException(GenericException GEX){
		ErrorResponse error =new ErrorResponse(GEX.getMessage());
		return ResponseEntity
				.status(HttpStatus.FORBIDDEN)
				.body(error);
	}
	
	
	 @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	    public ResponseEntity<Map<String, Object>> handleAllUncaughtExceptions(Exception ex) {
	        // You can also log the stack trace
	        ex.printStackTrace();

	        List<String> errors = List.of(ex.getClass().getName() + ": " + ex.getMessage());
	        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", errors);
	    }
	 
	    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message, List<String> errors) {
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("status", status.value());
	        body.put("error", status.getReasonPhrase());
	        body.put("message", message);
	        body.put("errors", errors);

	        return new ResponseEntity<>(body, status);
	    }

	
	
	
	
}
