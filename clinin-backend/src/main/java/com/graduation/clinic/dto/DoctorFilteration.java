package com.graduation.clinic.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.graduation.clinic.entity.Gender;
import com.graduation.clinic.entity.Specialties;

public class DoctorFilteration {

	
	private Gender sex;
	private Double totalRating;
	private Double lowPrice;
	private Double highPrice;
	private Integer experienceYears;
	private Specialties speciality;
	private String name;
	
	public DoctorFilteration(String name,Gender sex, Double totalRating, Double lowPrice, Double highPrice, Integer experienceYears,
			Specialties speciality) {
		this.sex = sex;
		this.totalRating = totalRating!=null? totalRating:null ;
		this.lowPrice = lowPrice!=null? lowPrice:null ;
		this.highPrice = highPrice!=null? highPrice:null ;
		this.experienceYears = experienceYears!=null? experienceYears:null  ;
		this.speciality = speciality;
		this.name=name;
	}

	public Gender getSex() {
		return sex;
	}

	public Double getTotalRating() {
		return totalRating;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public Double getHighPrice() {
		return highPrice;
	}

	public Integer getExperienceYears() {
		return experienceYears;
	}

	public Specialties getSpeciality() {
		return speciality;
	}

	public String getName() {
		return name;
	}



	
	
	
}
