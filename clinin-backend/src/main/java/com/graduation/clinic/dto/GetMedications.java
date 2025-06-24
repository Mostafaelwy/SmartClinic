package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Medications;

import jakarta.validation.constraints.NotNull;

public class GetMedications {


	private Long id;
	
	private String name;
	
	private String Dosage;
	
	private String duration;
	
	private String instructions;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDosage() {
		return Dosage;
	}

	public String getDuration() {
		return duration;
	}

	public String getInstructions() {
		return instructions;
	}

	public GetMedications(Medications m) {
		this.id = m.getId();
		this.name = m.getName();
		this.Dosage = m.getDosage();
		this.duration = m.getDuration();
		this.instructions = m.getInstructions();
	}
	
	
}
