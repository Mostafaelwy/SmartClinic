package com.graduation.clinic.service;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.VisitType;

import jakarta.persistence.criteria.Join;


public class ReservationSpecifications {

	public static Specification<Reservation> hasStatus(ReservationStatus status){
		return (root,query,criteriaBuilder)->status==null? null:
			criteriaBuilder.equal(root.get("status"),status);
	}
	
	public static Specification<Reservation> hasCreationDate(LocalDate startTime,LocalDate endTime){
		return(root,quert,criteriaBuilder)->(startTime==null || endTime==null ) ?null:
			criteriaBuilder.between(root.get("creationDate"),startTime, endTime);
	}
	
	public static Specification<Reservation> hasPatientName(String patientName){
		return (root,query,criteriaBuilder)->{
			if(patientName==null) return null;
			Join<Object, Object> patient= root.join("patient");
			return criteriaBuilder.like(criteriaBuilder.lower(patient.get("firstName")), "%"+ patientName.toLowerCase()+"%");
		};
	}
	
	public static Specification<Reservation> hasVisitType(VisitType visitType){
		return (root,query,criteriaBuilder)->visitType==null?null:
			criteriaBuilder.equal(root.get("visitType"), visitType);
	}
	
	public static Specification<Reservation> hasDoctorId(Long doctorId){
		return (root,query,criteriaBuilder)->doctorId==null?null:
			criteriaBuilder.equal( root.get("doctorId"), doctorId);
	}
}
