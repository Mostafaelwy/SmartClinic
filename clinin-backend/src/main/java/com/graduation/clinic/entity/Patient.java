package com.graduation.clinic.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Patient extends UsersBaseEntity {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "visitor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<ClinicsVistors> visitedClinics;
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Reservation> reservations;
	@OneToMany(mappedBy = "rater")
	private List<Rating> ratings;
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<AppointmentDetailes> patientAppointmentDetailes;
	@Enumerated(EnumType.STRING)
	private BloodGroub blood;
	private LocalDate dateOfBirth;
	
	
	
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public BloodGroub getBlood() {
		return blood;
	}

	public void setBlood(BloodGroub blood) {
		this.blood = blood;
	}

	public List<AppointmentDetailes> getPatientAppointmentDetailes() {
		return patientAppointmentDetailes;
	}

	public void setPatientAppointmentDetailes(List<AppointmentDetailes> patientAppointmentDetailes) {
		this.patientAppointmentDetailes = patientAppointmentDetailes;
	}



	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<ClinicsVistors> getVisitedClinics() {
		return visitedClinics;
	}

	public void setVisitedClinics(Set<ClinicsVistors> visitedClinics) {
		this.visitedClinics = visitedClinics;
	}
	




}
