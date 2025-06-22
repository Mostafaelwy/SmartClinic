package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Memberships;

public class MembershipsDto {

	private Long id;
	private String title;
	private String about;
	
	public String getTitle() {
		return title;
	}
	public String getAbout() {
		return about;
	}
	
	public Long getId() {
		return id;
	}
	public MembershipsDto(Memberships mem) {
		this.title = mem.getTitle();
		this.about = mem.getAbout();
		this.id=mem.getId();
	}
	
	
}
