package com.graduation.clinic.entity;



import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@MappedSuperclass
public abstract class UsersBaseEntity<ID> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;
	
	private int firstName;
	
	private int secondName;
	
	private Gender sex;
	
	private int age;
	
	private String country;
	
	@Pattern(regexp = "\\b(01[0-9]{9}|02[0-9]{8})\\b)")
	private List <String> phoneNumbers;
	
	@Email
	@NotNull
	public String email;
	
	@NotNull
	public String password;
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getFirstName() {
		return firstName;
	}

	public void setFirstName(int firstName) {
		this.firstName = firstName;
	}

	public int getSecondName() {
		return secondName;
	}

	public void setSecondName(int secondName) {
		this.secondName = secondName;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	

	
	
}
