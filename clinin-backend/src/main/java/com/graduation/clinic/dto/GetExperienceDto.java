package com.graduation.clinic.dto;

import java.time.LocalDate;

import com.graduation.clinic.entity.Employment;
import com.graduation.clinic.entity.Experience;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class GetExperienceDto {

	private Long id;
	private GetPhoto logo;
	private String title;
	private String hospital;
	private int experienceYears;
	private String location;
	@Enumerated(EnumType.STRING)
	private Employment employment;
	private String jopDescription;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean stillWorking;
	
	
	public GetExperienceDto(Experience e,GetPhoto p) {
		this.id = e.getId();
		this.logo = p;
		this.title = e.getTitle();
		this.hospital = e.getHospital();
		this.experienceYears = e.getExperienceYears();
		this.location = e.getLocation();
		this.employment = e.getEmployment();
		this.jopDescription = e.getJopDescription();
		this.startDate = e.getStartDate();
		this.endDate = e.getEndDate();
		this.stillWorking = e.isStillWorking();
	}
	public Long getId() {
		return id;
	}
	public GetPhoto getLogo() {
		return logo;
	}
	public String getTitle() {
		return title;
	}
	public String getHospital() {
		return hospital;
	}
	public int getExperienceYears() {
		return experienceYears;
	}
	public String getLocation() {
		return location;
	}
	public Employment getEmployment() {
		return employment;
	}
	public String getJopDescription() {
		return jopDescription;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public boolean isStillWorking() {
		return stillWorking;
	}
	
}
