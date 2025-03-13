package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Gender;

public class DoctorDto {
	

	
	private final Long id;
	
	private final String firstName;
	
	private final String secondName;
	
	private final Gender sex;
	
	private final String Specilization;
	
	private final int experienceYears;

	public DoctorDto(Doctor doctor) {
		this.id = doctor.getId();
		this.firstName = doctor.getFirstName();
		this.secondName = doctor.getSecondName();
		this.sex = doctor.getSex();
		this.Specilization = doctor.getSpecilization();
		this.experienceYears = doctor.getExperienceYears();
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

	public String getSpecilization() {
		return Specilization;
	}

	public int getExperienceYears() {
		return experienceYears;
	}
	

}
