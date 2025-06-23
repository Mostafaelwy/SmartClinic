package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.util.List;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.BloodGroub;
import com.graduation.clinic.entity.Patient;

import jakarta.validation.constraints.Email;

public class GetPatientProfileData {

	private Long patientId;
	private GetPhoto photo;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private List<String> phoneNumbers;
	@Email
	private String emailAddress;
	private BloodGroub groub;
	private Address address;
	
	
	public GetPatientProfileData(Patient p) {
		this.patientId=p.getId();
		this.photo = new GetPhoto(p.getProfilePhoto());
		this.firstName = p.getFirstName();
		this.lastName = p.getSecondName();
		this.dateOfBirth = p.getDateOfBirth();
		this.phoneNumbers = p.getPhoneNumbers();
		this.emailAddress = p.getUsername();
		this.groub = p.getBlood();
		this.address = p.getAddress();
	}
	
	
	public Long getPatientId() {
		return patientId;
	}
	public GetPhoto getPhoto() {
		return photo;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public BloodGroub getGroub() {
		return groub;
	}
	public Address getAddress() {
		return address;
	}
	
}
