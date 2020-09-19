package com.itinerarios.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.itinerarios.entity.ClaseVuelo;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ClaseVueloRepository extends CrudRepository<ClaseVuelo, Long> {

}