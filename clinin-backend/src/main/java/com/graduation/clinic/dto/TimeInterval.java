package com.graduation.clinic.dto;

import java.time.LocalTime;

import com.graduation.clinic.entity.Slot;

public class TimeInterval {

	private LocalTime startTime;
	private LocalTime endTime;
	
	public TimeInterval(Slot s) {
		this.startTime = s.getStartTime();
		this.endTime = s.getEndTime();
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	
	
}
