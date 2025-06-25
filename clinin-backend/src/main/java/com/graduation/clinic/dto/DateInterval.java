package com.graduation.clinic.dto;

import java.time.LocalDate;

public class DateInterval {

	private LocalDate startDate;
	private LocalDate endDate;
	
	public DateInterval(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	
}
