package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Gender;
import com.graduation.clinic.entity.Patient;

public class PatientData {

	private Long patientId;
	private GetPhoto photo;
	private String name;
	private Gender sex;
	private int age;
	
	
	public PatientData(Patient p) {
		this.patientId = p.getId();
		this.photo = new GetPhoto(p.getProfilePhoto());
		this.name = p.getFirstName()+p.getSecondName();
		this.sex = p.getSex();
		this.age = p.getAge();
	}
	public Long getPatientId() {
		return patientId;
	}
	public GetPhoto getPhoto() {
		return photo;
	}
	public String getName() {
		return name;
	}
	public Gender getSex() {
		return sex;
	}
	public int getAge() {
		return age;
	}
	
	
	
}
