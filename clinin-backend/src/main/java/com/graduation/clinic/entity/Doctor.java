package com.graduation.clinic.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class Doctor extends UsersBaseEntity {

	@NotNull
	private String Specilization;
	
	private int experienceYears;
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@NotEmpty
	private List <Clinic> workingClinics=new ArrayList<>();
	
	@OneToMany(mappedBy = "reviewedDoctor")
	@JsonBackReference
	private List<Review> DoctorReviews;
	
	public String getSpecilization() {
		return Specilization;
	}

	public void setSpecilization(String specilization) {
		Specilization = specilization;
	}
	public int getExperienceYears() {
		return experienceYears;
	}
	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}
	public List<Clinic> getWorkingClinics() {
		return workingClinics;
	}
	public void setWorkingClinics(List<Clinic> workingClinics) {
		this.workingClinics = workingClinics;
	}
	
}
