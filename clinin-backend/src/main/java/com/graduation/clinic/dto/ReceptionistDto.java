package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Gender;
import com.graduation.clinic.entity.Receptionist;

public class ReceptionistDto {
	private final Long id;
	
	private final String firstName;
	
	private final String secondName;
	
	private final Gender sex;
	
	private final int age;
	
	private final String country;
	
	private final String email;

	public ReceptionistDto(Receptionist recep) {
		this.id =recep.getId();
		this.firstName = recep.getFirstName();
		this.secondName = recep.getSecondName();
		this.sex = recep.getSex();
		this.age = recep.getAge();
		this.country = recep.getCountry();
		this.email = recep.email;
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
