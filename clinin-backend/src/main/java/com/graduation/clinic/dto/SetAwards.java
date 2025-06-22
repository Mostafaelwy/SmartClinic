package com.graduation.clinic.dto;

public class SetAwards {
	private Long id;
	private String awardName;
	private String year;
	private String description;
	
	public SetAwards(Long id, String awardName, String year, String description) {
		this.id = id;
		this.awardName = awardName;
		this.year = year;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public String getAwardName() {
		return awardName;
	}
	public String getYear() {
		return year;
	}
	public String getDescription() {
		return description;
	}
	
	
}
