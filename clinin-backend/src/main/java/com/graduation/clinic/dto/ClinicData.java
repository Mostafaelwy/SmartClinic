package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Days;

import jakarta.validation.constraints.NotNull;

public class ClinicData {

	@NotNull
	private final Long id;
	private final byte[] logo;
	@NotNull
	private final String clinicName;
	
	private final byte[] location;
	@NotNull
	private final List <String> phoneNumbers;
	@NotNull
	private final String openingTime;
	@NotNull
	private final String closingTime;
	
	private final List<Days>workingDays;
	
	
	public ClinicData(Clinic clinic) {
		this.id=clinic.getId();
		this.logo = clinic.getLogo();
		this.clinicName = clinic.getClinicName();
		this.location = clinic.getLocation();
		this.phoneNumbers = clinic.getPhoneNumbers();
		this.openingTime = clinic.getOpeningTime();
		this.closingTime =clinic.getClosingTime();
		this.workingDays = clinic.getWorkingDays();
	}


	public Long getId() {
		return id;
	}


	public byte[] getLogo() {
		return logo;
	}


	public String getClinicName() {
		return clinicName;
	}


	public byte[] getLocation() {
		return location;
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
	
}
