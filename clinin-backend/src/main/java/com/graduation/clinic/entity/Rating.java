package com.graduation.clinic.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;




@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private Doctor ratedDoctor;
	@NotNull
	@ManyToOne
	private Patient rater;

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private int rate;
	
	private String Review;
	
	private LocalDate creationDate;
	
	private String Reply;
	
	
	
	public String getReply() {
		return Reply;
	}
	public void setReply(String reply) {
		Reply = reply;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Doctor getRatedDoctor() {
		return ratedDoctor;
	}
	public void setRatedDoctor(Doctor ratedDoctor) {
		this.ratedDoctor = ratedDoctor;
	}
	public Patient getRater() {
		return rater;
	}
	public void setRater(Patient rater) {
		this.rater = rater;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	public String getReview() {
		return Review;
	}
	public void setReview(String review) {
		Review = review;
	}
	
	
	
	
}
