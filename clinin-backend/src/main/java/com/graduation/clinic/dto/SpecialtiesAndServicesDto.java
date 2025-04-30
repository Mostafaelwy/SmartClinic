package com.graduation.clinic.dto;

import java.util.ArrayList;
import java.util.List;

import com.graduation.clinic.entity.Specialties;
import com.graduation.clinic.entity.SpecialtiesAndServices;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SpecialtiesAndServicesDto {

	private Long id;
	
	private Specialties speciality;
	
	private List<SpecialityServiceDto> services;

	
	public SpecialtiesAndServicesDto(SpecialtiesAndServices request) {
		this.id = request.getId();
		this.speciality = request.getSpecialtiy();
		
		List<SpecialityServiceDto> serviceDto=new ArrayList<>();
		
		for(int i=0;i<request.getServices().size();i++) {
			serviceDto.add(new SpecialityServiceDto(request.getServices().get(i)));
		}
		
		this.services=serviceDto;
	}

	public Long getId() {
		return id;
	}

	public Specialties getSpeciality() {
		return speciality;
	}

	public List<SpecialityServiceDto> getServices() {
		return services;
	}
	
	
}
