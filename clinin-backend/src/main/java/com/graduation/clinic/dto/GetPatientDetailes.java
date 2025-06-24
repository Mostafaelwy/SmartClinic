package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Patient;

public class GetPatientDetailes {

	private String name;
	private Address address;
	
	
	public GetPatientDetailes(Patient p) {
		this.name = p.getFirstName()+p.getSecondName();
		this.address = p.getAddress();
	}
	public String getName() {
		return name;
	}
	public Address getAddress() {
		return address;
	}
	
	
}
