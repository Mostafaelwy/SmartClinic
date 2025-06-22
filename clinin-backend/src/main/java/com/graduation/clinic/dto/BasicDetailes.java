package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Languages;
import com.graduation.clinic.entity.Memberships;

public class BasicDetailes {

	private String firstName;
	private String lastName;
	private String DisplayName;
	private String Designation;
	private List <String> phoneNumbers;
	private String emailAddress;
	private GetPhoto photo;
	private List<Languages> langusgaes;
	private List<MembershipsDto> membershipsDto;
	
	public BasicDetailes(String firstName, String lastName, String displayName, String designation,
			List<String> phoneNumbers, String emailAddress, GetPhoto photo, List<Languages> langusgaes,
			List<MembershipsDto> membershipsDto) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.DisplayName = displayName;
		this.Designation = designation;
		this.phoneNumbers = phoneNumbers;
		this.emailAddress = emailAddress;
		this.photo = photo;
		this.langusgaes = langusgaes;
		this.membershipsDto = membershipsDto;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getDisplayName() {
		return DisplayName;
	}
	public String getDesignation() {
		return Designation;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public GetPhoto getPhoto() {
		return photo;
	}
	public List<Languages> getLangusgaes() {
		return langusgaes;
	}
	public List<MembershipsDto> getMembershipsDto() {
		return membershipsDto;
	}
	
	
}
