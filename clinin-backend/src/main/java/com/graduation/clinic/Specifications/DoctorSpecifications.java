package com.graduation.clinic.Specifications;



import org.springframework.data.jpa.domain.Specification;

import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Gender;
import com.graduation.clinic.entity.Specialties;

import jakarta.persistence.criteria.Join;

public class DoctorSpecifications {

	public static Specification<Doctor> hasGender(Gender gender){
		return (root,query,criteriaBuilder)->gender==null?null:
			criteriaBuilder.equal(root.get("sex"),gender);
		
	}
	public static Specification<Doctor> hasSpeciality(Specialties spec){
		return (root,query,criteriaBuilder)->{
			if(spec==null)return null;
			Join<Object,Object> DoctorSpecialties=root.join("doctorSpecilization");
			return criteriaBuilder.equal(DoctorSpecialties.get("speciality"),spec);
		};		
	}
	
	public static Specification<Doctor> hasServicePrice(double lowPrice,double highPrice){
		return (root,query,criteriaBuilder)->{
			if(highPrice==0)return null;
			Join<Object, Object> DoctorSpecialties=root.join("doctorSpecilization");
			Join<Object,Object> Service=DoctorSpecialties.join("services");
			return criteriaBuilder.between(Service.get("price"), lowPrice, highPrice);
		};
	}
	public static Specification<Doctor> hasExperienceYears(int years){
		return (root,query,criteriaBuilder)->years==0? null:
			criteriaBuilder.greaterThanOrEqualTo(root.get("experienceYears"), years);	
	}
	public static Specification<Doctor>hasRate(double rate){
		return (root,query,criteriaBuilder)->rate==0?null:
			criteriaBuilder.greaterThan(root.get("totalRating"), rate);
	}
}
