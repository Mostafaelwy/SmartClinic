package com.graduation.clinic.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Patient extends UsersBaseEntity {

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name ="birth_address_id")
	private Address placeOfBirth;
	
	@ManyToMany(mappedBy = "visitors")
	@JsonBackReference
	private List<Clinic> visitedClinics;
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

	public List<Clinic> getVisitedClinics() {
		return visitedClinics;
	}

	public void setVisitedClinics(List<Clinic> visitedClinics) {
		this.visitedClinics = visitedClinics;
	}


}
