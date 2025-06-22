package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Awards;

public class GetAwards {

	private Long id;
	private String awardName;
	private String year;
	private String description;
	
	public GetAwards(Awards a) {
		this.id = a.getId();
		this.awardName = a.getAwardName();
		this.year = a.getYear();
		this.description = a.getDescription();
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
