package com.graduation.clinic.entity;

import java.time.Duration;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "slot")
public class Slot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Clinic clinic;
	@Enumerated(EnumType.STRING)
	private Days day;
	@NotNull
	private LocalTime startTime;
	@NotNull
	private LocalTime endTime;
	@NotNull
	private Duration duration;
	@NotNull
	private Duration AppointmentInterval;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Clinic getClinic() {
		return clinic;
	}
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	public Days getDay() {
		return day;
	}
	public void setDay(Days day) {
		this.day = day;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public Duration getAppointmentInterval() {
		return AppointmentInterval;
	}
	public void setAppointmentInterval(Duration appointmentInterval) {
		AppointmentInterval = appointmentInterval;
	}




	
}
