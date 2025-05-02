package com.graduation.clinic.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
@Entity
public class SpecialtiesAndServices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private Doctor doctor;


	@NotNull
	@Enumerated(EnumType.STRING)
	private Specialties specialtiy;
	
	@OneToMany(mappedBy = "speciality",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@NotEmpty
	private List<SpecialityServices> services;
	
	
	
	
	public List<SpecialityServices> getServices() {
		return services;
	}
	public void setServices(List<SpecialityServices> services) {
		this.services = services;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Specialties getSpecialtiy() {
		return specialtiy;
	}
	public void setSpecialtiy(Specialties specialtiy) {
		this.specialtiy = specialtiy;
	}
//	public void addService(SpecialityServices service) {
//		services.add(service);
//		service.setSpeciality(this);
//	}
//	public void deleteService(SpecialityServices service) {
//		services.remove(service);
//		service.setSpeciality(null);
//	}


	

	
	
	
	
}
