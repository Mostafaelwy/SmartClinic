package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Days;

public class CreateClinicResponse {
	private final long id;
	
	private final String clinicName;
	
	private final Address address;
	
	private final List <String> phoneNumbers;
	
	private final String openingTime;
	
	private final String closingTime;
	
	private final List<Days> workingDays;
	
	//private final byte[] logo;
	
	//private final byte[] location;
	
	

	public CreateClinicResponse(Clinic clinic) {
		this.id = clinic.getId();
		this.clinicName = clinic.getClinicName();
		this.address = clinic.getAddress();
		this.phoneNumbers = clinic.getPhoneNumbers();
		this.openingTime = clinic.getOpeningTime();
		this.closingTime = clinic.getClosingTime();
		this.workingDays = clinic.getWorkingDays();
		//this.logo = clinic.getLogo();
		//this.location = clinic.getLocation();
	}

	public long getId() {
		return id;
	}

	public String getClinicName() {
		return clinicName;
	}

	public Address getAddress() {
		return address;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public List<Days> getWorkingDays() {
		return workingDays;
	}
/*
	public byte[] getLogo() {
		return logo;
	}

	public byte[] getLocation() {
		return location;
	}*/
	
}
