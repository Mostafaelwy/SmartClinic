package com.graduation.clinic.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {

	private Boolean Success;
	private String message;
	private LocalDateTime time;
	//private String Solution;
	
 

	public ErrorResponse( String message) {
		Success = Boolean.FALSE;
		this.message = message;
		this.time = LocalDateTime.now();
	}

	/*public ErrorResponse( String message, String solution) {
		Success =Boolean.FALSE;
		this.message = message;
		this.time = LocalDateTime.now();
		Solution = solution;
	}*/
	
	public Boolean getSuccess() {
		return Success;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTime() {
		return time;
	}

	/*public String getSolution() {
		return Solution;
	}*/
	
}

