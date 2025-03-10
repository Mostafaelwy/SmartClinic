package com.grauation.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grauation.clinic.entity.Receptionist;
@Repository
public interface ReceptionistRepo extends JpaRepository<Receptionist,Integer> {

}
