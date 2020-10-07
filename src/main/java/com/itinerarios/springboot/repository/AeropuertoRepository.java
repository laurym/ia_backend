package com.itinerarios.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.itinerarios.entity.Aeropuerto;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AeropuertoRepository extends CrudRepository<Aeropuerto, Long> {

	public Aeropuerto findByAcronimo(String acronimo);
}
