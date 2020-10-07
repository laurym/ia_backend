package com.itinerarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itinerarios.entity.Aerolinea;
import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.TipoClase;
import com.itinerarios.springboot.repository.AerolineaRepository;
import com.itinerarios.springboot.repository.AeropuertoRepository;
import com.itinerarios.springboot.repository.TipoClaseRepository;

@Service
public class BaseServiceImpl {
	@Autowired
	private AeropuertoRepository aeropuertoRepository;

	@Autowired
	private AerolineaRepository aerolineaRepository;

	@Autowired
	private TipoClaseRepository tipoClaseRepository;

	
	public  Iterable<Aeropuerto> findAllAeropuertos(){
		return aeropuertoRepository.findAll();
	}
	
	public Iterable<TipoClase> findAllTiposClase() {
		return tipoClaseRepository.findAll();
	}

	public Iterable<Aerolinea> findAllAerolineas() {
		return aerolineaRepository.findAll();
	}
	
	public Aerolinea save(Aerolinea entity) {
		return aerolineaRepository.save(entity);
	}
	
	public Aerolinea aerolineaByNombre(String nombreAerolinea) {
		return aerolineaRepository.findByNombre(nombreAerolinea);
	}

}
