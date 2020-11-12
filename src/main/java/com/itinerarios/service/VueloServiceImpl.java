package com.itinerarios.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public Aeropuerto buscarPorAcronimo (String acronimo) {
		
		return getAeropuertoRepository().findByAcronimo(acronimo);
	}
	
	public Page<Vuelo> paginas(Pageable pageable){
        return getVueloRepository().findAll(pageable);
    }
	
	public Iterable<Vuelo> buscarVuelos() {
		
		return getVueloRepository().findAll();
	}
	
	private Page<Vuelo> convertToPageable(List<Vuelo> vuelos, Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		int start = Math.min((int) paging.getOffset(), vuelos.size());
		int end = Math.min((start + paging.getPageSize()), vuelos.size());
		return new PageImpl<Vuelo>(vuelos.subList(start, end), paging, vuelos.size());
	}

	public Page<Vuelo> buscarVuelosPageable(Integer page, Integer size, String airportOrigin, String airportDest,
			Long asientosDisponibles, String date, String tipoClase) throws ExceptionServiceGeneral {

		Iterable<Vuelo> vuelosIterable = this.buscarVuelosDisponibles(airportOrigin, airportDest, asientosDisponibles, date, tipoClase);

		List<Vuelo> result = new ArrayList<Vuelo>();
		vuelosIterable.forEach(result::add);

		Page<Vuelo> pageToReturn = convertToPageable(result, page, size);
		return pageToReturn;

	}
	
	public Page<Vuelo> buscarVuelosPageable(Integer page, Integer size,String aerolineaCodigo, String airportOrigin, String airportDest,
			String date, String dateFin) throws ExceptionServiceGeneral {

		Iterable<Vuelo> vuelosIterable = this.buscarVuelosDisponibles(aerolineaCodigo, airportOrigin, airportDest, date, dateFin);

		List<Vuelo> result = new ArrayList<Vuelo>();
		vuelosIterable.forEach(result::add);

		Page<Vuelo> pageToReturn = convertToPageable(result, page, size);
		return pageToReturn;

	}
	
	public Iterable<Vuelo> buscarVuelosDisponibles(String aerolineaCodigo, String airportOrigin, String airportDest, String date, String dateFin) throws  ExceptionServiceGeneral{
		String mensajeError=null;
		Iterable<Vuelo> vuelos = null;
		Long airportOrId = null;
		Long airportDestId = null;
		
		try {
			
			if(airportOrigin != null && airportDest == null) {
				Aeropuerto origen = buscarPorAcronimo(airportOrigin.toUpperCase());
				airportOrId = origen.getId();
			} else if (airportDest != null && airportOrigin == null) {
				Aeropuerto destino = buscarPorAcronimo(airportOrigin.toUpperCase());
				airportDestId = destino.getId();
			} else 	if (airportOrigin != null && airportDest != null) {
				if (!airportOrigin.toUpperCase().equals(airportDest.toUpperCase())) {
					Aeropuerto origen = buscarPorAcronimo(airportOrigin.toUpperCase());
					Aeropuerto destino = buscarPorAcronimo(airportDest.toUpperCase());
					airportOrId = origen.getId();
					airportDestId = destino.getId();
//				if (date == null || date.isEmpty() && tipoClase ==null || tipoClase.isEmpty())
//					vuelos = getVueloRepository().buscarPorAeropuertoAeropuertoDestino(origin.getId(), destination.getId(), asientosDisponibles);

//				else 
//					if ((date != null || !date.isEmpty()) && (tipoClase ==null  || tipoClase.isEmpty()))
//					vuelos = getVueloRepository().buscarPorAeropuertoAeropuertoDestinoFecha(origin.getId(), destination.getId(), asientosDisponibles , date);

//				else
//				if ((date == null || date.isEmpty()) && (tipoClase != null && !tipoClase.isEmpty()))
//					vuelos = getVueloRepository().buscarPorAeropuertoAeropuertoDestinoClase(origin.getId(),
//							destination.getId(), asientosDisponibles, tipoClase);
//
//				else

				}	else {
					mensajeError = "05 -  ****** SYSTEM ERROR ****** búsqueda con error - "
							+ airportOrigin + " - " + airportDest;
					throw new ExceptionServiceGeneral(mensajeError);
				}
			}
			
			Aerolinea aerolinea = getAerolineaRepository().find(aerolineaCodigo);
			vuelos = getVueloRepository().buscarPorAerolineaAeropuertoAeropuertoDestinoFecha(airportOrId, airportDestId, aerolinea.getId(), date, dateFin);

		} 
		catch (QueryException e) {
		System.out.println(e.getMessage());
			mensajeError = "05 -  ****** SYSTEM ERROR ****** búsqueda con error - "
					+ airportOrigin + " - " + airportDest;
			throw new ExceptionServiceGeneral(mensajeError);
		}catch (RuntimeException e) {
			System.out.println(e.getMessage());

			mensajeError = "04 -  ****** PARSE ERROR ****** aeropuertos con error - "
					+ airportOrigin + " - " + airportDest;
			throw new ExceptionServiceGeneral(mensajeError);
		}
		return vuelos;
	}
	
	public Iterable<Vuelo> buscarVuelosDisponibles(String airportOrigin, String airportDest, Long asientosDisponibles,String date, String tipoClase) throws  ExceptionServiceGeneral{
		String mensajeError=null;
		Iterable<Vuelo> vuelos = null;
		try {
			if (!airportOrigin.toUpperCase().equals(airportDest.toUpperCase())) {
				Aeropuerto origin = buscarPorAcronimo(airportOrigin.toUpperCase());
				Aeropuerto destination = buscarPorAcronimo(airportDest.toUpperCase());
//				if (date == null || date.isEmpty() && tipoClase ==null || tipoClase.isEmpty())
//					vuelos = getVueloRepository().buscarPorAeropuertoAeropuertoDestino(origin.getId(), destination.getId(), asientosDisponibles);
				
//				else 
//					if ((date != null || !date.isEmpty()) && (tipoClase ==null  || tipoClase.isEmpty()))
//					vuelos = getVueloRepository().buscarPorAeropuertoAeropuertoDestinoFecha(origin.getId(), destination.getId(), asientosDisponibles , date);
				
//				else
				if ((date == null || date.isEmpty()) && (tipoClase != null && !tipoClase.isEmpty()))
					vuelos = getVueloRepository().buscarPorAeropuertoAeropuertoDestinoClase(origin.getId(),
							destination.getId(), asientosDisponibles, tipoClase);

				else
					vuelos = getVueloRepository().buscarPorAeropuertoAeropuertoDestinoFechaClase(origin.getId(), destination.getId(), asientosDisponibles, date, tipoClase);
					
			}

		} 
		catch (QueryException e) {
		System.out.println(e.getMessage());
			mensajeError = "05 -  ****** SYSTEM ERROR ****** búsqueda con error - "
					+ airportOrigin.toUpperCase() + " - " + airportDest.toUpperCase();
			throw new ExceptionServiceGeneral(mensajeError);
		}catch (RuntimeException e) {
			System.out.println(e.getMessage());

			mensajeError = "04 -  ****** PARSE ERROR ****** aeropuertos con error - "
					+ airportOrigin.toUpperCase() + " - " + airportDest.toUpperCase();
			throw new ExceptionServiceGeneral(mensajeError);
		}
		return vuelos;
	}

	public void saveVuelo(Vuelo entity) {
		
		Iterator<ClaseVuelo> itClasesVuelos = entity.getClases().iterator();
		Long contadorAsientos = 0L;
		while(itClasesVuelos.hasNext()) {
			ClaseVuelo idxObj = itClasesVuelos.next();
			contadorAsientos += idxObj.getAsientosVendidos();
		}
		entity.setAsientosVendidos(contadorAsientos);
		
		getVueloRepository().save(entity);
		
		itClasesVuelos = entity.getClases().iterator();
		
		while (itClasesVuelos.hasNext()) {
			ClaseVuelo idxObj = itClasesVuelos.next();
			ClaseVuelo claseBase = getClaseVueloRepository().find(entity.getCodigo(), idxObj.getCodigoClase().getCodigoClase());
			if (claseBase == null) {
				idxObj.setCodigoVuelo(entity);
				getClaseVueloRepository().save(idxObj);
			} else {
				claseBase.setAsientosClaseDisponibles(idxObj.getAsientosClaseDisponibles());
				claseBase.setAsientosVendidos(idxObj.getAsientosVendidos());
				claseBase.setPrecio(idxObj.getPrecio());
				getClaseVueloRepository().save(claseBase);
			}
		}
		
		getAerolineaRepository().save(entity.getAerolinea());
	}
	
	public Aerolinea buscarAerolinea(String codigoAerolinea) {
		return getAerolineaRepository().find(codigoAerolinea);
	}
	
	public Vuelo buscarVueloPorCodigo(String codigo){
		return getVueloRepository().findByCodigo(codigo);
	}
	
	public List<Vuelo> buscarVueloPorCodigoAerolinea(String codigoAerolinea){
		return getVueloRepository().findByAerolinea(codigoAerolinea);
	}
	
}
