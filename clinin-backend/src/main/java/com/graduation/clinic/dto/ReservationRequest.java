package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Days;

import jakarta.validation.constraints.NotNull;

public class ReservationRequest {

	@NotNull
	private long patientId;
	@NotNull
	private Days reservationDay;
	@NotNull
	private long clinicId;
	
	public long getPatientId() {
		return patientId;
	}

	public Days getReservationDay() {
		return reservationDay;
	}

	public long getClinicId() {
		return clinicId;
	}

	public ReservationRequest(@NotNull long patientId, @NotNull Days reservationDay, @NotNull long clinicId) {
		this.patientId = patientId;
		this.reservationDay = reservationDay;
		this.clinicId = clinicId;
	}
	



	
}
