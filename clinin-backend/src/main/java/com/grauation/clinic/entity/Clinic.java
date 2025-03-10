package com.grauation.clinic.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private int id;
	
	@NotNull
	private String clinicName;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@Pattern(regexp = "\\b(01[0-9]{9}|02[0-9]{8})\\b)")
	private  List <String> phoneNumbers;
	
	private String openingTime;
	
	private String closingTime;
	
	private Days workingDays;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Days getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(Days workingDays) {
		this.workingDays = workingDays;
	}
	
}
