package com.graduation.clinic.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Review;

import jakarta.persistence.ManyToOne;

public class ReviewDto {
	
	private final Long id;
	private final String message;
	private final PatientDto reviewer;
	private final int rate;
	//private final DoctorDto reviewedDoctor;
	
	
	public ReviewDto(Review review) {
		this.id = review.getId();
		this.message = review.getMessage();
		this.reviewer = new PatientDto( review.getReviewer());
		this.rate=review.getRate();
		//this.reviewedDoctor =new DoctorDto( review.getReviewedDoctor());
	}
	
	public Long getId() {
		return id;
	}
	public String getMessage() {
		return message;
	}
	public PatientDto getReviewer() {
		return reviewer;
	}

	public int getRate() {
		return rate;
	}
	
	/*
	public DoctorDto getReviewedDoctor() {
		return reviewedDoctor;
	}*/

}
