package com.graduation.clinic.dto;

import java.time.LocalDate;


import com.graduation.clinic.entity.VisitType;

import jakarta.validation.constraints.NotNull;


public class ReservationRequest {


	@NotNull
	private LocalDate reservationDate;
	@NotNull
	private VisitType visitType;

	
	public ReservationRequest(@NotNull LocalDate reservationDate,@NotNull VisitType visitType) {
		this.reservationDate = reservationDate;
		this.visitType = visitType;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	
	






	



	
}
