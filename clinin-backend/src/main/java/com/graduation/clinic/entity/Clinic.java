package com.graduation.clinic.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.graduation.clinic.dto.PatientDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "clinics")
public class Clinic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clinic_id")
	private long id;
	
	@NotNull
	private String clinicName;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	//@Pattern(regexp = "\\b(01[0-9]{9}|02[0-9]{8})\\b)")
	private  List <String> phoneNumbers;
	
	private String openingTime;
	
	private String closingTime;
	
	private List<Days>workingDays;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	@ManyToMany
	private List <Patient> visitors;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhone(List<String> phonenumbers) {
		this.phoneNumbers = phonenumbers;
	}
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	public List<Days> getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(List<Days> workingDays) {
		this.workingDays = workingDays;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public List<Patient> getVisitors() {
		return visitors;
	}
	public void setVisitors(List<Patient> visitors) {
		this.visitors = visitors;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	public void addVisitor(Patient visitor) {
		visitors.add(visitor);
	}
	
	
}
