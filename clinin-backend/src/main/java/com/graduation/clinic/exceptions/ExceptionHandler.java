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
	
}
