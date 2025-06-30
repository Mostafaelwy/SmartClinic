package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Languages;
import com.graduation.clinic.entity.Specialties;

public class DoctorData {

	private Long id;
	private double totalRating;
	private String firstName;
	private String SecondName;
	private Specialties speciality;
	private GetPhoto photo;
	private List<Languages> languages;
	
	
	
	
	public Long getId() {
		return id;
	}
	public double getTotalRating() {
		return totalRating;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return SecondName;
	}
	
	
	public Specialties getSpeciality() {
		return speciality;
	}
	public GetPhoto getPhoto() {
		return photo;
	}
	
	public List<Languages> getLanguages() {
		return languages;
	}
	public DoctorData(Doctor doc) {
		this.id=doc.getId();
		this.totalRating = doc.getTotalRating();
		this.firstName = doc.getFirstName();
		this.SecondName = doc.getSecondName();
		this.speciality=doc.getDoctorSpecilization().get(0).getSpeciality();
		this.photo=new  GetPhoto(doc.getProfilePhoto());
		this.languages=doc.getLanguages();
		
	}
	
	
}
