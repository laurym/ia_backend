package com.itinerarios.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.itinerarios.entity.Vuelo;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VueloRepository extends JpaRepository<Vuelo, String> , VueloRepositoryCustom {

//	@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and v.aeropuertoDestino.id = :aeropuertoDestino and v.fechaPartida= :date and v.clases.codigoClase = :tipoClase order by v.horaPartida asc")
//	Iterable<Vuelo> findByAeropuertoAeropuertoDestinoFechaClase(Long aeropuerto, Long aeropuertoDestino, String date, String tipoClase);

//	@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and v.aeropuertoDestino.id = :aeropuertoDestino and v.clases.codigoClase = :tipoClase  order by v.fechaPartida asc, v.horaPartida asc ")
//	Iterable<Vuelo> findByAeropuertoAeropuertoDestinoClase(Long aeropuerto, Long aeropuertoDestino, String tipoClase);
		
	@Query("from Vuelo v where v.codigo = :codigo")
	Vuelo findByCodigo(String codigo);
	
	@Query("from Vuelo v where v.aerolinea = :codigoAerolinea")
	List<Vuelo> findByAerolinea(String codigoAerolinea);
	
	@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and v.aeropuertoDestino.id = :aeropuertoDestino and v.fechaPartida= :date order by v.horaPartida asc")
	Iterable<Vuelo> buscarPorAeropuertoAeropuertoDestinoFecha(Long aeropuerto, Long aeropuertoDestino, String date);

	@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and v.aeropuertoDestino.id = :aeropuertoDestino order by v.fechaPartida asc, v.horaPartida asc ")
	Iterable<Vuelo> buscarPorAeropuertoAeropuertoDestino(Long aeropuerto, Long aeropuertoDestino);

}