package com.graduation.clinic.dto;

import java.time.LocalDate;

import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.VisitType;

public class ReservationDto {

	private Long id;
	private Long patientId;
	private String patientName;
	private String clinicName;
	private ReservationStatus status;
	private LocalDate reservationDate;
	private LocalDate creationDate;
	private VisitType visitType;
	private GetPhoto photo;
	
	
	public ReservationDto(Reservation reservation) {
		
		this.status = reservation.getStatus();
		this.id=reservation.getId();
		this.reservationDate=reservation.getReservationDate();
		this.patientName=reservation.getPatient().getFirstName()+reservation.getPatient().getSecondName();
		this.clinicName=reservation.getReservedClinic().getClinicName();
		this.creationDate=reservation.getCreationDate();
		this.visitType=reservation.getVisitType();
		this.photo=new GetPhoto(reservation.getPatient().getProfilePhoto());
	}


	public Long getId() {
		return id;
	}




	public Long getPatientId() {
		return patientId;
	}


	public GetPhoto getPhoto() {
		return photo;
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
