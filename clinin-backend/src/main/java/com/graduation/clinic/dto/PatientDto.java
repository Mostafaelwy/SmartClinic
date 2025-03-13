package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Gender;
import com.graduation.clinic.entity.Patient;

public class PatientDto {
	private final Long id;
	
	private final String firstName;
	
	private final String secondName;
	
	private final Gender sex;
	
	private final int age;
	
	private final String country;
	
	private final String email;
	

	public PatientDto(Patient patient) {
		this.id = patient.getId();
		this.firstName = patient.getFirstName();
		this.secondName = patient.getSecondName();
		this.sex = patient.getSex();
		this.age = patient.getAge();
		this.country = patient.getCountry();
		this.email=patient.getEmail();
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


	public Gender getSex() {
		return sex;
	}


	public int getAge() {
		return age;
	}


	public String getCountry() {
		return country;
	}


	public String getEmail() {
		return email;
	}
	
}
