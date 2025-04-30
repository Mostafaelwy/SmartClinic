package com.graduation.clinic.dto;

import java.time.LocalDate;

import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.VisitType;



public class FilterReservations {
	
	private ReservationStatus status;
	
	private LocalDate startTime;
	
	private LocalDate endTime;
	
	private VisitType visitType;
	
	private String patientName;



	public FilterReservations(ReservationStatus status, LocalDate startTime, LocalDate endTime, VisitType visitType,
			String patientName) {
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.visitType = visitType;
		this.patientName = patientName;
	}

	public ReservationStatus getStatus() {
		return status;
	}



	public LocalDate getStartTime() {
		return startTime;
	}

	public LocalDate getEndTime() {
		return endTime;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public String getPatientName() {
		return patientName;
	}
	
	
}
