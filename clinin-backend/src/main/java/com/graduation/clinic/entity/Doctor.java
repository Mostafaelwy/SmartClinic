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

	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SpecialtiesAndServices> doctorSpecilization;
	
	private int experienceYears;
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	
	private List <Clinic> workingClinics=new ArrayList<>();
	
	@OneToMany(mappedBy = "reviewedDoctor",cascade = CascadeType.ALL)
	private List<Review> DoctorReviews;
	
	@OneToMany(mappedBy = "ratedDoctor")
	private List<Rating> ratings;
	
	private double totalRating;
	
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<AppointmentDetailes> patientNotes;
	
	

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

	public List<Review> getDoctorReviews() {
		return DoctorReviews;
	}

	public void setDoctorReviews(List<Review> doctorReviews) {
		DoctorReviews = doctorReviews;
	}

	public double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(double totalRating) {
		this.totalRating = totalRating;
	}
	
//	public void addSpeciality(SpecialtiesAndServices speciality){
//		
//		doctorSpecilization.add(speciality);
//		speciality.setDoctor(this);
//	}
//	public void deleteSpeciality(SpecialtiesAndServices speciality){
//		doctorSpecilization.remove(speciality);
//		speciality.setDoctor(null);
//	}
//	
	

}
