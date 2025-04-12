package com.graduation.clinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PatientRegisterRequest {

	@NotBlank
	private String firstName;
	@NotBlank
	private String secondName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String Password;
	
	public PatientRegisterRequest(@NotBlank String firstName, @NotBlank String secondName,
			@NotBlank @Email String email, @NotBlank String password) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		Password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return Password;
	}
	
	
	
}
