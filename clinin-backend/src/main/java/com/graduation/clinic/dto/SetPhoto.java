package com.graduation.clinic.dto;

public class SetPhoto {
 
	private Long Id;
	private String url;
	private String type;
	
	public Long getId() {
		return Id;
	}

	public String getUrl() {
		return url;
	}

	public String getType() {
		return type;
	}

	public SetPhoto(Long id, String url, String type) {
		Id = id;
		this.url = url;
		this.type = type;
	}
	
	
}
