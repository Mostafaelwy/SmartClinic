package com.graduation.clinic.entity;

import java.util.List;

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
	
	@ManyToMany(mappedBy = "visitors")
	private List<Clinic> visitedClinics;
	
	@OneToMany(mappedBy ="reviewer", cascade = CascadeType.ALL)
	private List<Review> reviews;
	
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

	public List<Clinic> getVisitedClinics() {
		return visitedClinics;
	}

	public void setVisitedClinics(List<Clinic> visitedClinics) {
		this.visitedClinics = visitedClinics;
	}


}
