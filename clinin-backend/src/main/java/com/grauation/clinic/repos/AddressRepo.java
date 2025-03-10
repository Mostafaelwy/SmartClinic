package com.grauation.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grauation.clinic.entity.Address;
@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}
