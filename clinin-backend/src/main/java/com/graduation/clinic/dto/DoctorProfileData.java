package com.graduation.clinic.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.graduation.clinic.entity.Photo;
import com.graduation.clinic.entity.Specialties;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class DoctorProfileData {

	private String name ;
	
	private String DisplayName;
	
	@Enumerated(EnumType.STRING)
	private List<Specialties> specialites;
	
	private GetPhoto photo;
	
	public DoctorProfileData(String name, String displayName, List<Specialties> specialites, GetPhoto photo) {
		this.name = name;
		this.DisplayName = displayName;
		this.specialites = specialites;
		this.photo = photo;
	}

	

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return DisplayName;
	}

	public List<Specialties> getSpecialites() {
		return specialites;
	}

	public GetPhoto getPhoto() {
		return photo;
	}
	
}
