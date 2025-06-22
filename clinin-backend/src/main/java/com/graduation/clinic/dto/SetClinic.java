package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Photo;





public class SetClinic {
	
	private Long id;
	private SetPhoto logo;
	private String clinicName;
	private Address address;
	private String location;
	private List<SetPhoto> gellery;
	public Long getId() {
		return id;
	}
	public SetPhoto getLogo() {
		return logo;
	}
	public String getClinicName() {
		return clinicName;
	}
	public Address getAddress() {
		return address;
	}
	public String getLocation() {
		return location;
	}
	public List<SetPhoto> getGellery() {
		return gellery;
	}
	public SetClinic(Long id, SetPhoto logo, String clinicName, Address address, String location,
			List<SetPhoto> gellery) {
		
		this.id = id;
		this.logo = logo;
		this.clinicName = clinicName;
		this.address = address;
		this.location = location;
		this.gellery = gellery;
	}

	
}
