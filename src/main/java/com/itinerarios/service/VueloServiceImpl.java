package com.itinerarios.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.ClaseVuelo;
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
	
	public void saveVuelo(Vuelo entity) {
		
		getVueloRepository().save(entity);
		Iterator<ClaseVuelo> itClasesVuelos = entity.getClases().iterator();
		Boolean setearValor = Boolean.TRUE;
		System.out.println(" primerValor " + setearValor);
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
		
		setearValor = false;
		System.out.println(setearValor);
	}
}
