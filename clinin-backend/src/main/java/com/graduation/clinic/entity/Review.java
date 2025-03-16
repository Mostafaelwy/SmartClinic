package com.graduation.clinic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String message;
	@ManyToOne
	@JsonManagedReference
	private Patient reviewer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Doctor reviewedDoctor;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Patient getReviewer() {
		return reviewer;
	}

	public void setReviewer(Patient reviewer) {
		this.reviewer = reviewer;
	}

	public Doctor getReviewedDoctor() {
		return reviewedDoctor;
	}

	public void setReviewedDoctor(Doctor reviewedDoctor) {
		this.reviewedDoctor = reviewedDoctor;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
}
