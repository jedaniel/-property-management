package com.mycompany.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.PropertyEntity;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}
