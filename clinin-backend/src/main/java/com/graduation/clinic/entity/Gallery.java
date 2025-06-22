package com.graduation.clinic.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
@Entity
public class Gallery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "galleryId")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "clinicId")
	@NotNull
	private Clinic clinic;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "photoId")
	@NonNull
	private Photo photo;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Clinic getClinic() {
		return clinic;
	}
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	
}
