package com.graduation.clinic.entity;

import com.graduation.clinic.dto.MedicationCreation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
@Entity
public class Medications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String Dosage;
	@NotNull
	private String duration;
	
	private String instructions;
	@ManyToOne
	private AppointmentDetailes appointmentDetailes;
	
	
	
	public Medications(MedicationCreation request) {
		this.name = request.getName();
		this.Dosage = request.getDosage();
		this.duration = request.getDuration();
		this.instructions = request.getInstructions();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AppointmentDetailes getAppointmentDetailes() {
		return appointmentDetailes;
	}
	public void setAppointmentDetailes(AppointmentDetailes appointmentDetailes) {
		this.appointmentDetailes = appointmentDetailes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDosage() {
		return Dosage;
	}
	public void setDosage(String dosage) {
		Dosage = dosage;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	
}
