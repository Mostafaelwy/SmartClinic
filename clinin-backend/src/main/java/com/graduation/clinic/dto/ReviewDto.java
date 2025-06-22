package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Rating;

public class ReviewDto {
	private Long id;
	private int rate;
	private String Review;
	private String patientUserName;
	
	
	public ReviewDto(Rating rating) {
		this.id=rating.getId();
		this.rate = rating.getRate();
		this.Review = rating.getReview();
		this.patientUserName=rating.getRater().getUsername();
	}
	public int getRate() {
		return rate;
	}
	public String getReview() {
		return Review;
	}
	public Long getId() {
		return id;
	}
	public String getPatientUserName() {
		return patientUserName;
	}
	
	
	

}
