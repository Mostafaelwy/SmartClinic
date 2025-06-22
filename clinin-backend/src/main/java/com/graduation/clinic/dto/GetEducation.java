package com.graduation.clinic.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.graduation.clinic.entity.Education;

import jakarta.persistence.Column;

public class GetEducation {
	private Long id;

	private GetPhoto logo;
	private String institutionName;
	private String course;
	private LocalDate startDate;
	private LocalDate endDate;
	private int yearsNum;
	private String description;
	
	public GetEducation(Education e) {
		this.id = e.getId();
		this.logo = new GetPhoto(e.getLogo());
		this.institutionName = e.getInstitutionName();
		this.course = e.getCourse();
		this.startDate = e.getStartDate();
		this.endDate = e.getEndDate();
		this.yearsNum = e.getYearsNum();
		this.description = e.getDescription();
	}
	public Long getId() {
		return id;
	}
	public GetPhoto getLogo() {
		return logo;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public String getCourse() {
		return course;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public int getYearsNum() {
		return yearsNum;
	}
	public String getDescription() {
		return description;
	}
	

}
