package com.graduation.clinic.entity;


import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.graduation.clinic.dto.SetClinic;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "clinics")
public class Clinic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="clinic_id")
	private Long id;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "photoId")
	private Photo logo;
	@NotNull
	private String clinicName;
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	@Column(name="clinic_location")
	private String location;
	//@Pattern(regexp = "\\b(01[0-9]{9}|02[0-9]{8})\\b)")
	private  List <String> phoneNumbers;

	private String openingTime;
	private String closingTime;
	
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @MapKeyEnumerated(EnumType.STRING)
	private SortedMap<DayOfWeek,Slot>workingDays =new TreeMap<>();
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	@JsonBackReference
	@NotNull
	private Doctor doctor;
	
	@OneToMany(mappedBy = "clinic",cascade = CascadeType.ALL)
	private Set<ClinicsVistors> myVistors;
	
	@OneToMany(mappedBy = "reservedClinic",cascade = CascadeType.ALL)
	private List <Reservation> reservations;


	@Column(name = "photosId")
	private List<Long> gallery;
	@OneToMany(mappedBy = "clinic",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ReservedTime> reservedTimes;
	
	public Clinic() {
	}
	



	public Clinic( SetClinic c,List<Long> gallery) {
		this.id = c.getId();
		this.logo = new Photo(c.getLogo());
		this.clinicName = c.getLocation();
		this.address = c.getAddress();
		this.location = c.getLocation();
		this.gallery = gallery;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Photo getLogo() {
		return logo;
	}

	public void setLogo(Photo logo) {
		this.logo = logo;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
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

	public SortedMap<DayOfWeek, Slot> getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(SortedMap<DayOfWeek, Slot> workingDays) {
		this.workingDays = workingDays;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Set<ClinicsVistors> getMyVistors() {
		return myVistors;
	}

	public void setMyVistors(Set<ClinicsVistors> myVistors) {
		this.myVistors = myVistors;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Long> getGallery() {
		return gallery;
	}

	public void setGallery(List<Long> gallery) {
		this.gallery = gallery;
	}




	public List<ReservedTime> getReservedTimes() {
		return reservedTimes;
	}




	public void setReservedTimes(List<ReservedTime> reservedTimes) {
		this.reservedTimes = reservedTimes;
	}

}