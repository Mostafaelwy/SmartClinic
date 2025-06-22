package com.graduation.clinic.entity;

import com.graduation.clinic.dto.SetMemberships;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Memberships {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	@ManyToOne
	private Doctor doctor;
	private String title;
	private String about;
	
	
	
	public Memberships() {
	}
	public Memberships(SetMemberships m) {
		this.id = m.getId();
		this.title = m.getTitle();
		this.about = m.getAbout();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
}
