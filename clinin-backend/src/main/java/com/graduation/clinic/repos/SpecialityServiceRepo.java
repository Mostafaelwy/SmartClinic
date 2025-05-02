package com.graduation.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduation.clinic.entity.SpecialityServices;

public interface SpecialityServiceRepo extends JpaRepository<SpecialityServices,Long> {

}
