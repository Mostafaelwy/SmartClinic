package com.graduation.clinic.entity;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.graduation.clinic.dto.ReviewDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String message;
	@ManyToOne(cascade = CascadeType.ALL)
	private Patient reviewer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Doctor reviewedDoctor;
	
	private LocalDate creationDate;
	


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

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	





	
}
