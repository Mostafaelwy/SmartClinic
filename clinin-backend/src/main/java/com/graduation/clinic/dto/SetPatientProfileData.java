package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.util.List;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.BloodGroub;

import jakarta.validation.constraints.Email;

public class SetPatientProfileData {
	private SetPhoto photo;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private List<String> phoneNumbers;
	@Email
	private String emailAddress;
	private BloodGroub groub;
	private Address address;
	
	
	public SetPatientProfileData(SetPhoto photo, String firstName, String lastName, LocalDate dateOfBirth,
			List<String> phoneNumbers, @Email String emailAddress, BloodGroub groub, Address address) {
		this.photo = photo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumbers = phoneNumbers;
		this.emailAddress = emailAddress;
		this.groub = groub;
		this.address = address;
	}
	public SetPhoto getPhoto() {
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
