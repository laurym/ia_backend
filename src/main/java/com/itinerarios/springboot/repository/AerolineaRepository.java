package com.itinerarios.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.itinerarios.entity.Aerolinea;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AerolineaRepository extends CrudRepository<Aerolinea, Long> {

	@Query("from Aerolinea where codigoAerolinea = :codigoAerolinea ")
	public Aerolinea find(@Param("codigoAerolinea") String codigoAerolinea);

}