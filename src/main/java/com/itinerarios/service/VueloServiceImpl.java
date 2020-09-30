package com.itinerarios.service;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itinerarios.entity.Aerolinea;
import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.ClaseVuelo;
import com.itinerarios.entity.Vuelo;
import com.itinerarios.exceptions.ExceptionServiceGeneral;
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
	
	public Iterable<Vuelo> findAvailableFlights(String airportOrigin, String airportDest, Date date) throws  ExceptionServiceGeneral{
		String mensajeError=null;
		Iterable<Vuelo> vuelos = null;
		try {
			if (!airportOrigin.toUpperCase().equals(airportDest.toUpperCase())) {
				Aeropuerto origin = findByAcronimo(airportOrigin.toUpperCase());
				Aeropuerto destination = findByAcronimo(airportDest.toUpperCase());
				
				vuelos = getVueloRepository().findByByAeropuertoAeropuertoDestino(origin.getId(), destination.getId());
			}

		} catch (RuntimeException e) {
			mensajeError = "04 -  ****** PARSE ERROR ****** aeropuertos con error - "
					+ airportOrigin.toUpperCase() + " - " + airportDest.toUpperCase();
			throw new ExceptionServiceGeneral(mensajeError);
		}
		return vuelos;
	}

	public void saveVuelo(Vuelo entity) {
		
		getVueloRepository().save(entity);
		Iterator<ClaseVuelo> itClasesVuelos = entity.getClases().iterator();
		while (itClasesVuelos.hasNext()) {
			ClaseVuelo idxObj = itClasesVuelos.next();
			ClaseVuelo claseBase = getClaseVueloRepository().find(entity.getCodigo(), idxObj.getCodigoClase().getCodigoClase());
			if (claseBase == null) {
				getClaseVueloRepository().save(idxObj);
			} else {
				claseBase.setAsientosClaseDisponibles(idxObj.getAsientosClaseDisponibles());
				claseBase.setPrecio(idxObj.getPrecio());
				getClaseVueloRepository().save(claseBase);
			}
		}
		
		getAerolineaRepository().save(entity.getAerolinea());
	}
	
	public Aerolinea findAerolinea(String codigoAerolinea) {
		return getAerolineaRepository().find(codigoAerolinea);
	}
}
