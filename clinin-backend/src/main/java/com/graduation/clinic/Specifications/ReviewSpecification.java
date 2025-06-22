package com.graduation.clinic.Specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.graduation.clinic.entity.Rating;
import com.graduation.clinic.entity.Reservation;


import jakarta.persistence.criteria.Join;

public class ReviewSpecification {

	public static Specification<Rating> hasTimeInterval(LocalDate startDate,LocalDate endDate){
		return (root,query,criteriaBuilder)->(startDate==null || endDate==null) ? null:
			criteriaBuilder.between(root.get("creationDate"),startDate, endDate);
	}
	public static Specification<Rating> hasDoctorId(Long doctorId){
		return (root,query,criteriaBuilder)->{
			if(doctorId==null)return null;
			Join<Object, Object> doctor=root.join("ratedDoctor");
			return criteriaBuilder.equal(doctor.get("id"),doctorId );
		};
			
	}
	
}
