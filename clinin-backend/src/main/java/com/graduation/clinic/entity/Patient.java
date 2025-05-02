package com.graduation.clinic.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Patient extends UsersBaseEntity {

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name ="birth_address_id")
	private Address placeOfBirth;
	@OneToMany(mappedBy = "visitor",cascade = CascadeType.ALL)
	private Set<ClinicsVistors> visitedClinics;
	
	@OneToMany(mappedBy ="reviewer", cascade = CascadeType.ALL)
	private List<Review> reviews;
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
	private List<Reservation> reservations;
	@OneToMany(mappedBy = "rater")
	private List<Rating> ratings;
	
	private List<AppointmentDetailes> patientAppointmentDetailes;
	
	
	
	public List<AppointmentDetailes> getPatientAppointmentDetailes() {
		return patientAppointmentDetailes;
	}

	public void setPatientAppointmentDetailes(List<AppointmentDetailes> patientAppointmentDetailes) {
		this.patientAppointmentDetailes = patientAppointmentDetailes;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(Address placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Set<ClinicsVistors> getVisitedClinics() {
		return visitedClinics;
	}

	public void setVisitedClinics(Set<ClinicsVistors> visitedClinics) {
		this.visitedClinics = visitedClinics;
	}
	




}
