package com.graduation.clinic.dto;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Slot;

import jakarta.validation.constraints.NotNull;

public class UpdateClinicDetailesRequest {
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
	
	private final SortedMap<Days,Slot>workingDays =new TreeMap<>();


	

	public UpdateClinicDetailesRequest(@NotNull Long id, byte[] logo, @NotNull String clinicName, byte[] location,
			@NotNull List<String> phoneNumbers, @NotNull String openingTime, @NotNull String closingTime) {
		this.id = id;
		this.logo = logo;
		this.clinicName = clinicName;
		this.location = location;
		this.phoneNumbers = phoneNumbers;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
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


	public SortedMap<Days, Slot> getWorkingDays() {
		return workingDays;
	}




	
	
}
