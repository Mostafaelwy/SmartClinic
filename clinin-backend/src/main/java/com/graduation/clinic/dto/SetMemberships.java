package com.graduation.clinic.dto;

public class SetMemberships {

	private Long id;
	private String title;
	private String about;
	
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getAbout() {
		return about;
	}
	public SetMemberships(Long id, String title, String about) {
		this.id = id;
		this.title = title;
		this.about = about;
	}
	
	
}
