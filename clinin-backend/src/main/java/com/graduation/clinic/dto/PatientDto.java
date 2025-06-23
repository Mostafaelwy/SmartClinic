package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.BloodGroub;
import com.graduation.clinic.entity.Gender;
import com.graduation.clinic.entity.Patient;

public class PatientDto {
	private final Long id;
	
	private final String firstName;
	
	private final String secondName;
	
	private final Gender sex;
	
	private final int age;
	
	private final Address address;
	private final String userName;
	
	private final List<String> phoneNumbers;
	
	private BloodGroub blood;
	


	public PatientDto(Patient patient) {
		this.id = patient.getId();
		this.firstName = patient.getFirstName();
		this.secondName = patient.getSecondName();
		this.sex = patient.getSex();
		this.age = patient.getAge();
		this.userName=patient.getUsername();
		this.phoneNumbers=patient.getPhoneNumbers();
		this.address=patient.getAddress();
		this.blood=patient.getBlood();
		
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



	public String getUserName() {
		return userName;
	}


	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}


	public Address getAddress() {
		return address;
	}


	public BloodGroub getBlood() {
		return blood;
	}
	
	
	
}
