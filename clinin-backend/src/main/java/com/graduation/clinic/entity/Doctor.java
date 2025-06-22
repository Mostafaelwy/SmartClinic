package com.graduation.clinic.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class Doctor extends UsersBaseEntity {

	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SpecialtiesAndServices> doctorSpecilization;
	
	private int experienceYears;
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List <Clinic> workingClinics=new ArrayList<>();
	

	
	@OneToMany(mappedBy = "ratedDoctor")
	private List<Rating> ratings;
	
	private double totalRating;
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<AppointmentDetailes> patientNotes;
	
	private String Designation;
	private String displayName;
	@Enumerated(EnumType.STRING)
	private List<Languages> languages;
	@OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Memberships> memberships;
	@OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Experience> experience;
	@OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Education> education;
	@OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Awards> awards;	
	
	public List<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	public List<AppointmentDetailes> getPatientNotes() {
		return patientNotes;
	}
	public void setPatientNotes(List<AppointmentDetailes> patientNotes) {
		this.patientNotes = patientNotes;
	}
	public List<SpecialtiesAndServices> getDoctorSpecilization() {
		return doctorSpecilization;
	}
	public void setDoctorSpecilization(List<SpecialtiesAndServices> doctorSpecilization) {
		this.doctorSpecilization = doctorSpecilization;
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


	public double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(double totalRating) {
		this.totalRating = totalRating;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<Languages> getLanguages() {
		return languages;
	}
	public void setLanguages(List<Languages> languages) {
		this.languages = languages;
	}
	public List<Memberships> getMemberships() {
		return memberships;
	}
	public void setMemberships(List<Memberships> memberships) {
		this.memberships = memberships;
	}
	public List<Experience> getExperience() {
		return experience;
	}
	public void setExperience(List<Experience> experience) {
		this.experience = experience;
	}
	public List<Education> getEducation() {
		return education;
	}
	public void setEducation(List<Education> education) {
		this.education = education;
	}
	public List<Awards> getAwards() {
		return awards;
	}
	public void setAwards(List<Awards> awards) {
		this.awards = awards;
	}
	
	

	

}
