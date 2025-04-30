package com.graduation.clinic.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
@Entity
public class SpecialityServices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@ManyToOne
	@NotNull
	private SpecialtiesAndServices speciality;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private  SpecialityService serviceType;
	private double price;
	private String hint;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SpecialtiesAndServices getSpeciality() {
		return speciality;
	}
	public void setSpeciality(SpecialtiesAndServices speciality) {
		this.speciality = speciality;
	}
	public SpecialityService getServiceType() {
		return serviceType;
	}
	public void setServiceType(SpecialityService serviceType) {
		this.serviceType = serviceType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	
	
	
}
