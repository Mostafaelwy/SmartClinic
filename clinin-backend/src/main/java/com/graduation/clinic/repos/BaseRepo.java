package com.graduation.clinic.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import com.graduation.clinic.entity.UsersBaseEntity;

@NoRepositoryBean
public interface BaseRepo <T extends UsersBaseEntity<ID>,ID extends Number> extends JpaRepository<T, ID> {

	Optional <T> findById(ID id);
}
