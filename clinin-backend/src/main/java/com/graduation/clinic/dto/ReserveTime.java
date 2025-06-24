package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReserveTime {

	
	private LocalDate date;
	private LocalTime time;
	
	public ReserveTime(LocalDate date, LocalTime time) {
		this.date = date;
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public LocalTime getTime() {
		return time;
	}
	

	
	
}
