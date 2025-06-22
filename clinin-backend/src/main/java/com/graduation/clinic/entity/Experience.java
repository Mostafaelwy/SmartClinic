package com.graduation.clinic.entity;

import java.time.LocalDate;

import com.graduation.clinic.dto.SetExperienceRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Doctor doctor;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "photoId")
	private Photo logo;
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
	
	
	public Experience() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Photo getLogo() {
		return logo;
	}
	public void setLogo(Photo logo) {
		this.logo = logo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public int getExperienceYears() {
		return experienceYears;
	}
	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Employment getEmployment() {
		return employment;
	}
	public void setEmployment(Employment employment) {
		this.employment = employment;
	}
	public String getJopDescription() {
		return jopDescription;
	}
	public void setJopDescription(String jopDescription) {
		this.jopDescription = jopDescription;
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
	public boolean isStillWorking() {
		return stillWorking;
	}
	public void setStillWorking(boolean stillWorking) {
		this.stillWorking = stillWorking;
	}
	public Experience(SetExperienceRequest e) {
		this.id = e.getId();
		this.logo = new Photo(e.getLogo());
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
	
	
	
	
}
