package com.graduation.clinic.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.SpecialtiesAndServices;
@Repository
public interface SpecialtiesAndServicesRepo extends JpaRepository<SpecialtiesAndServices, Long> {

	List<SpecialtiesAndServices> findByDoctorId(Long doctorId);
	
	
	@Query("DELETE FROM SpecialtiesAndServices sas WHERE sas.id = :id")
    void deleteByIdCustom(@Param("id") Long id);
}
