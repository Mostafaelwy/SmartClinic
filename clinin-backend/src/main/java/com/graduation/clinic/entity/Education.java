package com.graduation.clinic.entity;

import java.time.LocalDate;

import com.graduation.clinic.dto.SetEducation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
@Entity
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Doctor doctor;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "photoId")
	private Photo logo;
	private String institutionName;
	private String course;
	private LocalDate startDate;
	private LocalDate endDate;
	private int yearsNum;
	private String description;
	
	public Education(SetEducation e) {
		this.id = e.getId();
		this.logo = new Photo(e.getLogo());
		this.institutionName = e.getInstitutionName();
		this.course = e.getCourse();
		this.startDate = e.getStartDate();
		this.endDate = e.getEndDate();
		this.yearsNum = e.getYearsNum();
		this.description = e.getDescription();
	}
	
	public Education() {
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Photo getLogo() {
		return logo;
	}
	public void setLogo(Photo logo) {
		this.logo = logo;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public int getYearsNum() {
		return yearsNum;
	}
	public void setYearsNum(int yearsNum) {
		this.yearsNum = yearsNum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	

}
