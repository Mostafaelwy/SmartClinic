package com.graduation.clinic.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WriteReviewRequest {

	@NotNull
	private Long doctorId;
	@NotNull
	private Long reviewerId;
	@NotNull
	private String message;
	@NotNull
	@Min(value =0)
	@Max(value = 5)
	private int rate;
	
	

	public WriteReviewRequest(@NotNull Long doctorId, @NotNull Long reviewerId, @NotNull String message,
			@NotNull @Min(0) @Max(5) int rate) {
		this.doctorId = doctorId;
		this.reviewerId = reviewerId;
		this.message = message;
		this.rate = rate;
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
	public int getRate() {
		return rate;
	}
	
	
}
