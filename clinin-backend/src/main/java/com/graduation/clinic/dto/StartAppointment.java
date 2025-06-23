package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.BloodGroub;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.VisitType;

public class StartAppointment {

	private PatientDto patient;
	private int patientVisitsNum;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
	private String clinicName;
	private Address clinicLocation;
	private VisitType visitType;
	private String cost;
	
	
	
	public StartAppointment(Reservation reservation , int patientVisitsNum) {
		this.patient = new PatientDto(reservation.getPatient());
		this.patientVisitsNum = patientVisitsNum;
		this.appointmentDate = reservation.getReservationDate();
		this.clinicName = reservation.getReservedClinic().getClinicName();
		this.clinicLocation = reservation.getReservedClinic().getAddress();
		this.visitType = reservation.getVisitType();
		this.appointmentTime=reservation.getReservationTime();
		this.cost=reservation.getCost();
	}

	public PatientDto getPatient() {
		return patient;
	}

	public int getPatientVisitsNum() {
		return patientVisitsNum;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public String getClinicName() {
		return clinicName;
	}

	public Address getClinicLocation() {
		return clinicLocation;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public String getCost() {
		return cost;
	}
	
	
	
	
}
