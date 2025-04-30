package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingDto {

	@NotNull
	private Long doctorId;
	@NotNull
	private Long raterId;
	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private int rate;
	

	
	public RatingDto(@NotNull Long doctorId, @NotNull Long raterId, @NotNull @Min(1) @Max(5) int rate) {
		this.doctorId = doctorId;
		this.raterId = raterId;
		this.rate = rate;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public Long getRaterId() {
		return raterId;
	}

	public int getRate() {
		return rate;
	}
	
	
	
}
