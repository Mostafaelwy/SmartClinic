package com.graduation.clinic.repos;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.ClinicsVistors;

@Repository
public interface ClinicsVisitorsRepo extends JpaRepository<ClinicsVistors, Long> {

	Long countByDoctorId(Long doctorId);
	Long countByDoctorIdAndVisitationDate(Long id,LocalDate date);
}
