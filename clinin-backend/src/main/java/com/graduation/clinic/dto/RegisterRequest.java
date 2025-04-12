package com.graduation.clinic.dto;

public class RegisterRequest {

	
	
	private String firstName;
	private String secondName;
	private String email;
	private String Password;
	
	
	public RegisterRequest(String firstName, String secondName, String email, String password) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.Password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public String getemail() {
		return email;
	}
	public String getPassword() {
		return Password;
	}
}
