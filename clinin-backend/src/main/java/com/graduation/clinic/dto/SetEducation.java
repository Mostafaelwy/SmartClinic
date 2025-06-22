package com.graduation.clinic.dto;

import java.time.LocalDate;

public class SetEducation {

	private Long id;
	private SetPhoto logo;
	private String institutionName;
	private String course;
	private LocalDate startDate;
	private LocalDate endDate;
	private int yearsNum;
	private String description;
	public Long getId() {
		return id;
	}
	public SetPhoto getLogo() {
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
	public SetEducation(Long id, SetPhoto logo, String institutionName, String course, LocalDate startDate,
			LocalDate endDate, int yearsNum, String description) {
		this.id = id;
		this.logo = logo;
		this.institutionName = institutionName;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.yearsNum = yearsNum;
		this.description = description;
	}
	
}
