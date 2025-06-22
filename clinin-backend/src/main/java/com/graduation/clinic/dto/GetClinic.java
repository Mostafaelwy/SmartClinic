package com.graduation.clinic.dto;

import java.util.ArrayList;
import java.util.List;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Clinic;

public class GetClinic {

	private Long id;
	private GetPhoto logo;
	private String clinicName;
	private Address address;
	private String location;
	private List<GetPhoto> gellery;
	
	public GetClinic(Clinic c) {
		this.id = c.getId();
		this.logo = new GetPhoto(c.getLogo());
		this.clinicName = c.getClinicName();
		this.address = c.getAddress();
		this.location = c.getLocation();
		
		List<GetPhoto> g=new ArrayList<>();
		for(int i=0;i<c.getGallery().size();i++) {
			g.add( new GetPhoto(c.getGallery().get(i).getPhoto()));
		}
		this.gellery=g;
	}
	public Long getId() {
		return id;
	}
	public GetPhoto getLogo() {
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
	public List<GetPhoto> getGellery() {
		return gellery;
	}
	
	
}
