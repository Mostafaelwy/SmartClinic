package com.graduation.clinic.repos;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.AppointmentDetailes;
@Repository
public interface AppointmentDetailesRepo extends JpaRepository<AppointmentDetailes, Long>{

	int countByDoctorIdAndPatientId(Long doctorId,Long patientId) ;
		
	Page<AppointmentDetailes> findByPatientId(Long patientId,Pageable page);
	
}
