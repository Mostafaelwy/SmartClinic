package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Gender;
import com.graduation.clinic.entity.UsersBaseEntity;

public class UserDto {
	
	
	
	
	
	private final Long id;
	
	private final String firstName;
	
	private final String secondName;
	private final String userName;
	private final Gender sex;
	

	public UserDto(UsersBaseEntity user) {
		this.firstName = user.getFirstName();
		this.secondName = user.getSecondName();
		this.userName=user.getUsername();
		this.id = user.getId();
		this.sex=user.getSex();
	}

	public String getUserName() {
		return userName;
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
}
