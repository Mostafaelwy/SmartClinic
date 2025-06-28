package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Photo;

public class GetPhoto {

	private Long Id;
	private String URl;
	private String type;
	
	
	public GetPhoto(Photo p) {
		Id = p.getId();
		URl = p.getURl();
		this.type = p.getType();
	}
	
	public Long getId() {
		return Id;
	}
	public String getURl() {
		return URl;
	}
	public String getType() {
		return type;
	}
	
}
