package com.itinerarios.springboot.repository;

import java.util.List;

import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.Vuelo;

public interface VueloRepositoryCustom {

//	@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and v.aeropuertoDestino.id = :aeropuertoDestino and v.fechaPartida= :date and v.clases.codigoClase = :tipoClase order by v.horaPartida asc")
	List<Vuelo> buscarPorAeropuertoAeropuertoDestinoFechaClase(Long aeropuerto, Long aeropuertoDestino, Long asientosDisponibles, String date, String tipoClase);

//	@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and v.aeropuertoDestino.id = :aeropuertoDestino and v.clases.codigoClase = :tipoClase  order by v.fechaPartida asc, v.horaPartida asc ")
	List<Vuelo> buscarPorAeropuertoAeropuertoDestinoClase(Long aeropuerto, Long aeropuertoDestino, Long asientosDisponibles, String tipoClase);

//	List<Vuelo> buscarPorCodigoAerolinea(String codigoAerolinea);
	
    public List<Vuelo> buscarPorAerolineaAeropuertoAeropuertoDestinoFecha(Long aeropuerto, Long aeropuertoDestino, Long aerolineaCodigo, String date);

}
