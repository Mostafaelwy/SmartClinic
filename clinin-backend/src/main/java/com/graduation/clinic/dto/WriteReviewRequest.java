package com.graduation.clinic.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WriteReviewRequest {

	@NotNull
	private Long doctorId;

	@NotNull
	private String message;

	
	

	public WriteReviewRequest(@NotNull Long doctorId, @NotNull String message) {
		this.doctorId = doctorId;
		this.message = message;
	}
	public Long getDoctorId() {
		return doctorId;
	}

	public String getMessage() {
		return message;
	}

	
	
}
