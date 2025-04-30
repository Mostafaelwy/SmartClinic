package com.graduation.clinic.repos;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> ,JpaSpecificationExecutor<Reservation>{
	
	
	Page<Reservation> findByDoctorId(Long doctorId,Specification<Reservation> spec,Pageable page);
	
	
	
	
	
	
	
	Page<Reservation> findByReservedClinicId(Long id,Pageable page);
	
	Page<Reservation> findByReservedClinicIdAndStatus(Long id,ReservationStatus Status,Pageable page);
	
	Page<Reservation> findByDoctorIdAndReservationDateBetween(Long doctorId,LocalDate startDate,LocalDate endDate,Pageable page);
	Page<Reservation> findByDoctorIdAndReservationDateBetweenAndStatus(Long doctorId,LocalDate startDate,LocalDate endDate,ReservationStatus status,Pageable page);
	Page<Reservation> findByDoctorId(Long doctorId,Pageable page);
	Page<Reservation> findByDoctorIdAndStatus(Long doctorId,ReservationStatus status,Pageable page);
	
	Long countByDoctorIdAndStatusAndReservationDate(Long doctorId,ReservationStatus Status,LocalDate date);
	
	
//	Page<Reservation> findByReservedClinicId(Long id,Pageable page);
	

}
