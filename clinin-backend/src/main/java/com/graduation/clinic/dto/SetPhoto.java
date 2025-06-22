package com.graduation.clinic.dto;

public class SetPhoto {
 
	private Long Id;
	private byte [] URl;
	private String type;
	
	public Long getId() {
		return Id;
	}

	public byte[] getURl() {
		return URl;
	}

	public String getType() {
		return type;
	}

	public SetPhoto(Long id, byte[] uRl, String type) {
		Id = id;
		URl = uRl;
		this.type = type;
	}
	
	
}
