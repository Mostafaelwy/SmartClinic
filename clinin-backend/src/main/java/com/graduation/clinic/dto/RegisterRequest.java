package com.graduation.clinic.dto;

public class RegisterRequest {

	private String firstName;
	private String secondName;
	private String userName;
	private String Password;
	
	
	public RegisterRequest(String firstName, String secondName, String userName, String password) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.userName = userName;
		this.Password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return Password;
	}
}
