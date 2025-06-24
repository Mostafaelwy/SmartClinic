package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.graduation.clinic.entity.VisitType;

import jakarta.validation.constraints.NotNull;


public class ReservationRequest {


	@NotNull
	private LocalDate reservationDate;
	@NotNull
	private LocalTime reservationTime;
	@NotNull
	private VisitType visitType;
	@NotNull
	private Long serviceId;

	


	public ReservationRequest(@NotNull LocalDate reservationDate, @NotNull LocalTime reservationTime,
			@NotNull VisitType visitType,@NotNull Long serviceId) {
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.visitType = visitType;
		
		this.serviceId=serviceId;
	}
	
	

	public LocalTime getReservationTime() {
		return reservationTime;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public Long getServiceId() {
		return serviceId;
	}

	
	






	



	
}
