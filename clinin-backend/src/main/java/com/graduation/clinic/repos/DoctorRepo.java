package com.graduation.clinic.repos;



import java.util.List;
import java.util.Optional;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import com.graduation.clinic.entity.Doctor;



@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> ,JpaSpecificationExecutor<Doctor>{

	List <Doctor> findAll();
	Optional <Doctor> findByUserName (String userName);
	//Page<Doctor> findDoctor(Specification<Doctor> specification ,Pageable page);
	
	
}
