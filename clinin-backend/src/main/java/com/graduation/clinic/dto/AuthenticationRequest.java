package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AuthenticationRequest {
	


	private String email;

	private String password;

	public AuthenticationRequest( String email, String password) {
		this.email = email;
		this.password = password;
	}


	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	
	
	
}
