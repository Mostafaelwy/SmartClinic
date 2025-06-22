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
	
	public DoctorFilteration(Gender sex, Double totalRating, Double lowPrice, Double highPrice, Integer experienceYears,
			Specialties speciality) {
		this.sex = sex;
		this.totalRating = totalRating!=null? totalRating:0 ;
		this.lowPrice = lowPrice!=null? lowPrice:0 ;
		this.highPrice = highPrice!=null? highPrice:0 ;
		this.experienceYears = experienceYears!=null? experienceYears:0  ;
		this.speciality = speciality;
	}

	public Gender getSex() {
		return sex;
	}

	public double getTotalRating() {
		return totalRating;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public Specialties getSpeciality() {
		return speciality;
	}



	
	
	
}
