package com.itinerarios.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.itinerarios.entity.ClaseVuelo;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ClaseVueloRepository extends CrudRepository<ClaseVuelo, Long> {

	@Query("from ClaseVuelo where codigoClase.codigoClase = :codigoClase  and codigoVuelo.codigo = :codigoVuelo")
	public ClaseVuelo find(@Param("codigoVuelo") String codigoVuelo, @Param("codigoClase") String tipoClase);

}
