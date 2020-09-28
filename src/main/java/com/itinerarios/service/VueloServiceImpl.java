package com.itinerarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.Vuelo;
import com.itinerarios.springboot.repository.AerolineaRepository;
import com.itinerarios.springboot.repository.AeropuertoRepository;
import com.itinerarios.springboot.repository.ClaseVueloRepository;
import com.itinerarios.springboot.repository.TipoClaseRepository;
import com.itinerarios.springboot.repository.VueloRepository;

@Service
public class VueloServiceImpl {

	@Autowired
	private AeropuertoRepository aeropuertoRepository;

	@Autowired
	private AerolineaRepository aerolineaRepository;

	@Autowired
	private VueloRepository VueloRepository;

	@Autowired
	private TipoClaseRepository tipoClaseRepository;

	@Autowired
	private ClaseVueloRepository claseVueloRepository;

	
	public AeropuertoRepository getAeropuertoRepository() {
		return aeropuertoRepository;
	}

	public AerolineaRepository getAerolineaRepository() {
		return aerolineaRepository;
	}

	public VueloRepository getVueloRepository() {
		return VueloRepository;
	}

	public TipoClaseRepository getTipoClaseRepository() {
		return tipoClaseRepository;
	}

	public ClaseVueloRepository getClaseVueloRepository() {
		return claseVueloRepository;
	}

	public Aeropuerto findByAcronimo (String acronimo) {
		
		return getAeropuertoRepository().findByAcronimo(acronimo);
	}
	
	public Iterable<Vuelo> findAllVuelos() {
		
		return getVueloRepository().findAll();
	}
}
