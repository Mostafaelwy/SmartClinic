package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Doctor;

public class DoctorData {

	private Long id;
	private double totalRating;
	private String firstName;
	private String SecondName;
	
	
	public Long getId() {
		return id;
	}
	public double getTotalRating() {
		return totalRating;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return SecondName;
	}
	
	
	public DoctorData(Doctor doc) {
		this.id=doc.getId();
		this.totalRating = doc.getTotalRating();
		this.firstName = doc.getFirstName();
		this.SecondName = doc.getSecondName();
	}
	
	
}
