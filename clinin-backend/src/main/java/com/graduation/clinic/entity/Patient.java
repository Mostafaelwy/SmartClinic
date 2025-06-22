package com.graduation.clinic.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@OneToMany(mappedBy = "visitor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<ClinicsVistors> visitedClinics;
	
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Reservation> reservations;
	@OneToMany(mappedBy = "rater")
	private List<Rating> ratings;
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<AppointmentDetailes> patientAppointmentDetailes;
	
	
	
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
