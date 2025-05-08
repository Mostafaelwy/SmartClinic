package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingDto {

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private int rate;
	

	
	public RatingDto(@NotNull @Min(1) @Max(5) int rate) {

		
		this.rate = rate;
	}


	public int getRate() {
		return rate;
	}
	
	
	
}
