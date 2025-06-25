package com.graduation.clinic.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.graduation.clinic.dto.ReserveTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class ReservedTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservedTimeId")
	private Long id;
	private LocalDate date;
	private LocalTime time;
	@ManyToOne
	private Clinic clinic;
	
	
	
	public ReservedTime() {
;
	}
	public ReservedTime(ReserveTime t,Clinic c) {
		this.date = t.getDate();
		this.time = t.getTime();
		this.clinic=c;
	}
	public Long getId() {
		return id;
	}
	public LocalDate getDate() {
		return date;
	}
	public LocalTime getTime() {
		return time;
	}
	public Clinic getClinic() {
		return clinic;
	}
	
	
}
