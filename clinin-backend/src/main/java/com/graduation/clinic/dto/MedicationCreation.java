package com.graduation.clinic.dto;

import jakarta.validation.constraints.NotNull;

public class MedicationCreation {

	@NotNull
	private String name;

	@NotNull
	private String Dosage;
	@NotNull
	private String duration;
	
	private String instructions;

	public MedicationCreation(@NotNull String name,@NotNull String dosage,@NotNull String duration, String instructions) {
		this.name = name;
		Dosage = dosage;
		this.duration = duration;
		this.instructions = instructions;
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
	
}
