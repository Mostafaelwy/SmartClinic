package com.graduation.clinic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="reservations")
public class Reservation {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@Enumerated(EnumType.STRING)
	private Days reservationDay;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@ManyToOne
	@JoinColumn(name = "reserved_clinic")
	private Clinic reservedClinic;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Days getReservationDay() {
		return reservationDay;
	}

	public void setReservationDay(Days reservationDay) {
		this.reservationDay = reservationDay;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public Clinic getReservedClinic() {
		return reservedClinic;
	}

	public void setReservedClinic(Clinic reservedClinic) {
		this.reservedClinic = reservedClinic;
	}
	
}

