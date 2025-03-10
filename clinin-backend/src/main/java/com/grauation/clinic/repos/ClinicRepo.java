package com.grauation.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grauation.clinic.entity.Clinic;
@Repository
public interface ClinicRepo  extends JpaRepository<Clinic, Integer>{

}
