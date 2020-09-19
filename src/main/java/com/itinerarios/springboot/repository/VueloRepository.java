package com.itinerarios.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.itinerarios.entity.Vuelo;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VueloRepository extends CrudRepository<Vuelo, String> {

}