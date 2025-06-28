package com.graduation.clinic.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.Collate;

import com.graduation.clinic.dto.SetPhoto;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photoId")
	private Long Id;


	@Lob
	private String url;
	private String type;

	
	
	public Photo() {
	}
	public Photo(SetPhoto p) {
		Id = p.getId();
		url = p.getUrl();
		this.type = p.getType();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getURl() {
		return url;
	}
	public void setURl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	
}
