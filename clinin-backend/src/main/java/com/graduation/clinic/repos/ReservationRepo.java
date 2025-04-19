package com.graduation.clinic.repos;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{
	
	Page<Reservation> findByReservedClinicId(Long id,Pageable page);
	
	Page<Reservation> findByReservedClinicIdAndStatus(Long id,ReservationStatus Status,Pageable page);
	
	
//	Page<Reservation> findByReservedClinicId(Long id,Pageable page);
	

}
