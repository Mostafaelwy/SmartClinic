package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;

public class ReservationDto {

	public final Long id;
	public final String userName;
	public final Days reservationDay;
	public final ReservationStatus status;
	
	public ReservationDto(Reservation reservation) {
		this.userName = reservation.getPatient().getUsername();
		this.reservationDay = reservation.getReservationDay();
		this.status = reservation.getStatus();
		this.id=reservation.getId();
	}

	public String getUserName() {
		return userName;
	}

	public Days getReservationDay() {
		return reservationDay;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
}
