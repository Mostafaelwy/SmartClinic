package com.graduation.clinic.dto;

import java.util.List;


import jakarta.validation.constraints.NotNull;

public class DoctorBasicDetailesRequest {
	@NotNull
	private final Long id;
	@NotNull
	private final String firstName;
	@NotNull
	private final String secondName;
	@NotNull
	private final int age;
	@NotNull
	private final List <String> phoneNumbers;

	private final byte[] photo;
	@NotNull
	private final String Specilization;
	@NotNull
	private final int experienceYears;
	public DoctorBasicDetailesRequest(@NotNull Long id, String firstName, String secondName, int age,
			List<String> phoneNumbers, byte[] photo, String specilization, int experienceYears) {
		this.id=id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.age = age;
		this.phoneNumbers = phoneNumbers;
		this.photo = photo;
		this.Specilization = specilization;
		this.experienceYears = experienceYears;
	}
	

	public Long getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public int getAge() {
		return age;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public String getSpecilization() {
		return Specilization;
	}
	public int getExperienceYears() {
		return experienceYears;
	}
	

}
