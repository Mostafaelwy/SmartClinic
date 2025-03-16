package com.graduation.clinic.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException NFEX){
		ErrorResponse error= new ErrorResponse(NFEX.getMessage());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(error);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(DuplicateException.class)
	public ResponseEntity<?> HandleDuplicateException(DuplicateException DEX){
		ErrorResponse error=new ErrorResponse(DEX.getMessage());
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(error);
		
	}
}
