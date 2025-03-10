package com.grauation.clinic.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import com.grauation.clinic.entity.UsersBaseEntity;

//@NoRepositoryBean
@Component
public interface BaseRepo <T extends UsersBaseEntity<ID>,ID extends Number> extends JpaRepository<T, ID> {

	Optional <T> findById(ID id);
}
