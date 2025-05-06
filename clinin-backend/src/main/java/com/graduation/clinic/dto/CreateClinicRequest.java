package com.graduation.clinic.dto;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Slot;

import jakarta.validation.constraints.NotNull;

public class CreateClinicRequest {
	@NotNull
	private  String clinicName;
	@NotNull
	private  Address address;
	@NotNull
	private  List <String> phoneNumbers;
	@NotNull
	private  String openingTime;
	@NotNull
	private  String closingTime;


	
	public CreateClinicRequest(@NotNull String clinicName, @NotNull Address address, @NotNull List<String> phoneNumbers,
			@NotNull String openingTime, @NotNull String closingTime) {
		this.clinicName = clinicName;
		this.address = address;
		this.phoneNumbers = phoneNumbers;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		
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



	
	
}
