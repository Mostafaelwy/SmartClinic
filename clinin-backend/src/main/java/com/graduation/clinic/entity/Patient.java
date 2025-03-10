package com.graduation.clinic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Patient extends UsersBaseEntity<Integer> {

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToOne
	@JoinColumn(name ="birth_address_id")
	private Address placeOfBirth;
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


}
