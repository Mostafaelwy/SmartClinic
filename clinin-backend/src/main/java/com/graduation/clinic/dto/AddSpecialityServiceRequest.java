package com.graduation.clinic.dto;

import com.graduation.clinic.entity.SpecialityService;

import jakarta.validation.constraints.NotNull;

public class AddSpecialityServiceRequest {

	@NotNull
	private  SpecialityService serviceType;
	@NotNull
	private double price;
	
	private String hint;
	
	

	public AddSpecialityServiceRequest(@NotNull SpecialityService serviceType, @NotNull double price, String hint) {
		this.serviceType = serviceType;
		this.price = price;
		this.hint = hint;
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
