package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Doctor;

public class DoctorBasicDetailes {

	private final Long id ;
	private final String firstName;
	private final String secondName;
	private final int age;
	private final List <String> phoneNumbers;
	private final byte[] photo;
	
	private final int experienceYears;
	

	public DoctorBasicDetailes(Doctor doctor) {
		this.id=doctor.getId();
		this.firstName = doctor.getFirstName();
		this.secondName = doctor.getSecondName();
		this.age = doctor.getAge();
		this.phoneNumbers = doctor.getPhoneNumbers();
		this.photo = doctor.getPhoto();
		
		this.experienceYears = doctor.getExperienceYears();
	
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


	public int getExperienceYears() {
		return experienceYears;
	}
	
	
	
}
