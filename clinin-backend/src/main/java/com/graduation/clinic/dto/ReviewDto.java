package com.graduation.clinic.dto;

import java.time.LocalDate;

import com.graduation.clinic.entity.Rating;

public class ReviewDto {
	private Long id;
	private int rate;
	private String Review;
	private Long patientId;
	private String patientName;
	private LocalDate creatDate;
	private GetPhoto patientPhoto;
	private String reply;
	
	
	
	public ReviewDto(Rating rating) {
		this.id=rating.getId();
		this.rate = rating.getRate();
		this.Review = rating.getReview();
		this.patientId=rating.getRater().getId();
		this.patientName=rating.getRater().getFirstName()+rating.getRater().getSecondName();
		this.creatDate=rating.getCreationDate();
		this.patientPhoto=new GetPhoto(rating.getRater().getProfilePhoto());
		this.reply=rating.getReply();
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
	public Long getPatientId() {
		return patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public LocalDate getCreatDate() {
		return creatDate;
	}
	public GetPhoto getPatientPhoto() {
		return patientPhoto;
	}
	public String getReply() {
		return reply;
	}

	
	
	

}
