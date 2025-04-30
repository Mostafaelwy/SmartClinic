package com.graduation.clinic.dto;

import com.graduation.clinic.entity.SpecialityService;
import com.graduation.clinic.entity.SpecialityServices;


public class SpecialityServiceDto {

	private Long id;
	
	private  SpecialityService serviceType;
	
	private double price;
	
	private String hint;

	public SpecialityServiceDto(SpecialityServices service) {
		this.id = service.getId();
		this.serviceType =service.getServiceType();
		this.price =service.getPrice() ;
		this.hint = service.getHint();
	}

	public Long getId() {
		return id;
	}

	public SpecialityService getServiceType() {
		return serviceType;
	}

	public double getPrice() {
		return price;
	}

	public String getHint() {
		return hint;
	}
	
	
	
	
}
