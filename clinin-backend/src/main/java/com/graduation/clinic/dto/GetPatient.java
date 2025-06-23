package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.BloodGroub;
import com.graduation.clinic.entity.Gender;
import com.graduation.clinic.entity.Reservation;

public class GetPatient {

	private Long patientId;
	private String name;
	private int age;
	private Gender sex;
	private BloodGroub blood;
	private Address address;
	private LocalDate reservationDate;
	private LocalTime reservationTime;
	
	public Long getPatientId() {
		return patientId;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public Gender getSex() {
		return sex;
	}
	public BloodGroub getBlood() {
		return blood;
	}
	public Address getAddress() {
		return address;
	}
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	public LocalTime getReservationTime() {
		return reservationTime;
	}
	
	public GetPatient(Reservation r) {
		this.patientId = r.getPatient().getId();
		this.name = r.getPatient().getFirstName()+r.getPatient().getSecondName();
		this.age = r.getPatient().getAge();
		this.sex = r.getPatient().getSex();
		this.blood = r.getPatient().getBlood();
		this.address = r.getPatient().getAddress();
		this.reservationDate = r.getReservationDate();
		this.reservationTime = r.getReservationTime();
	}
	
	
}
