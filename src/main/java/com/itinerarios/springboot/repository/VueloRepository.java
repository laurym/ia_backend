package com.itinerarios.springboot.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.itinerarios.entity.Vuelo;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VueloRepository extends CrudRepository<Vuelo, String> {

	@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and v.aeropuertoDestino.id = :aeropuertoDestino and v.fechaPartida= :date")
	Iterable<Vuelo> findByByDateAeropuertoAeropuertoDestino(Long aeropuerto, Long aeropuertoDestino, Date date);

	@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and v.aeropuertoDestino.id = :aeropuertoDestino")
	Iterable<Vuelo> findByByAeropuertoAeropuertoDestino(Long aeropuerto, Long aeropuertoDestino);

}