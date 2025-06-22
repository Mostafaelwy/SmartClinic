package com.graduation.clinic.dto;

import java.time.LocalDate;

import com.graduation.clinic.entity.Employment;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class SetExperienceRequest {

	private Long id;
	private SetPhoto logo;
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
	
	
	public SetExperienceRequest(Long id, SetPhoto logo, String title, String hospital, int experienceYears,
			String location, Employment employment, String jopDescription, LocalDate startDate, LocalDate endDate,
			boolean stillWorking) {
		this.id = id;
		this.logo = logo;
		this.title = title;
		this.hospital = hospital;
		this.experienceYears = experienceYears;
		this.location = location;
		this.employment = employment;
		this.jopDescription = jopDescription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.stillWorking = stillWorking;
	}
	
	public Long getId() {
		return id;
	}
	public SetPhoto getLogo() {
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
