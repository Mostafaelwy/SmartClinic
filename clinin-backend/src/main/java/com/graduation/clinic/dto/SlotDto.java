package com.graduation.clinic.dto;

import java.time.Duration;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public class 	SlotDto {
	@NotNull
	private LocalTime startTime;
	@NotNull
	private LocalTime endTime;
	@NotNull
	private Duration duration;
	@NotNull
	private Duration interval;
	public SlotDto(@NotNull LocalTime startTime, @NotNull LocalTime endTime, @NotNull Duration duration,
			@NotNull Duration interval) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.interval = interval;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public Duration getDuration() {
		return duration;
	}
	public Duration getInterval() {
		return interval;
	}
	


}
