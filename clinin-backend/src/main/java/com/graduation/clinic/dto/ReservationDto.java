package com.graduation.clinic.dto;

import java.time.LocalDate;

import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.VisitType;

public class ReservationDto {

	private Long id;
	private String patientUserName;
	private String patientName;
	private String clinicName;
	private ReservationStatus status;
	private LocalDate reservationDate;
	private LocalDate creationDate;
	private VisitType visitType;
	
	
	public ReservationDto(Reservation reservation) {
		this.patientUserName = reservation.getPatient().getUsername();
		this.status = reservation.getStatus();
		this.id=reservation.getId();
		this.reservationDate=reservation.getReservationDate();
		this.patientName=reservation.getPatient().getFirstName();
		this.clinicName=reservation.getReservedClinic().getClinicName();
		this.creationDate=reservation.getCreationDate();
		this.visitType=reservation.getVisitType();
	}


	public Long getId() {
		return id;
	}


	public String getPatientUserName() {
		return patientUserName;
	}


	public String getPatientName() {
		return patientName;
	}


	public String getClinicName() {
		return clinicName;
	}


	public ReservationStatus getStatus() {
		return status;
	}


	public LocalDate getReservationDate() {
		return reservationDate;
	}


	public LocalDate getCreationDate() {
		return creationDate;
	}


	public VisitType getVisitType() {
		return visitType;
	}
	


	
	
	
	
	
}
