package com.graduation.clinic.dto;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Photo;
import com.graduation.clinic.entity.Slot;

public class GetClinic {

	private Long id;
	private GetPhoto logo;
	private String clinicName;
	private Address address;
	private String location;
	private List<GetPhoto> gellery;
	private SortedMap<DayOfWeek,TimeInterval>workingHoursMap =new TreeMap<>();
	
	public GetClinic(Clinic c,List<Photo> photos)  {
		this.id = c.getId();
		this.logo = new GetPhoto(c.getLogo());
		this.clinicName = c.getClinicName();
		this.address = c.getAddress();
		this.location = c.getLocation();
		List<GetPhoto> getPhotos=new ArrayList<>();
		for(int i=0;i<photos.size();i++) {
			getPhotos.add(new GetPhoto(photos.get(i)));
		}
		this.gellery=getPhotos;
		
		c.getWorkingDays().forEach((day, slot) -> {
		    workingHoursMap.put(day, new TimeInterval(slot));
		});
		
		

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
	public SortedMap<DayOfWeek, TimeInterval> getWorkingHoursMap() {
		return workingHoursMap;
	}
	
	
}
