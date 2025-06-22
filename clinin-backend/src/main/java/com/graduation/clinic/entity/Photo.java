package com.graduation.clinic.entity;


import org.hibernate.annotations.Collate;

import com.graduation.clinic.dto.SetPhoto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photoId")
	private Long Id;
	private byte [] URl;
	private String type;

	
	
	
	public Photo() {
	}
	public Photo(SetPhoto p) {
		Id = p.getId();
		URl = p.getURl();
		this.type = p.getType();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public byte[] getURl() {
		return URl;
	}
	public void setURl(byte[] uRl) {
		URl = uRl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	
}
