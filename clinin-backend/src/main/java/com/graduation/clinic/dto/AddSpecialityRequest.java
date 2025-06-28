package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Specialties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddSpecialityRequest {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	private Specialties speciality;
	@NotEmpty
	private List<AddSpecialityServiceRequest> services;
	
	public AddSpecialityRequest(@NotNull Specialties speciality, @NotEmpty List<AddSpecialityServiceRequest> services) {
		this.speciality = speciality;
		this.services = services;
	}

	public Specialties getSpeciality() {
		return speciality;
	}

	public List<AddSpecialityServiceRequest> getServices() {
		return services;
	}

	
	
}
