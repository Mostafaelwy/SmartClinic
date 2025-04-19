package com.graduation.clinic.dto;

import jakarta.validation.constraints.NotNull;

public class WriteReviewRequest {

	@NotNull
	private Long doctorId;
	@NotNull
	private Long reviewerId;
	@NotNull
	private String message;
	
	public WriteReviewRequest(Long doctorId, Long reviewerId, String message) {
		this.doctorId = doctorId;
		this.reviewerId = reviewerId;
		this.message = message;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public Long getReviewerId() {
		return reviewerId;
	}
	public String getMessage() {
		return message;
	}
	
}
