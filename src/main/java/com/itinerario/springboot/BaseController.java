package com.itinerario.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itinerarios.dto.AerolineaDTO;
import com.itinerarios.dto.AeropuertoDTO;
import com.itinerarios.entity.Greeting;
import com.itinerarios.enums.TipoClase;

@RestController
@RequestMapping("rest")
public class BaseController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	Logger LOG = LogManager.getLogger(BaseController.class);

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		LOG.info("***** Inicio  greeting *****");
		LOG.info("***** Fin  greeting *****");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/aeropuertos")
	public AeropuertoDTO obtenerAeropuertos() {
		LOG.info("***** Inicio  obtenerAeropuertos *****");
		LOG.info("***** Fin  obtenerAeropuertos *****");
		AeropuertoDTO aeropuertoDTO = new AeropuertoDTO ();
		aeropuertoDTO.setAcronimo("AR");
		aeropuertoDTO.setCiudad("Buenos Aires");
		aeropuertoDTO.setPais("Argentina");
		
		return aeropuertoDTO;
	}
	
	
	@GetMapping("/aerolineas")
	public AerolineaDTO obtenerAerolineas() {
		LOG.info("***** Inicio  obtenerAeropuertos *****");
		LOG.info("***** Fin  obtenerAeropuertos *****");
		AerolineaDTO aerolineaDTO = new AerolineaDTO ();
		aerolineaDTO.setCodigoAerolinea("AR");
		aerolineaDTO.setNombreAerolinea("Aerolineas Argentinas");
		aerolineaDTO.setPorcentajeDescuentoMenores(50L);
		
		return aerolineaDTO;
	}
	
	@GetMapping("/clases")
	public List<TipoClase> obtenerClases(){ 
		LOG.info("***** Inicio  obtenerClases *****");
		LOG.info("***** Fin  obtenerClases *****");
		List<TipoClase> listTipoClases = new ArrayList<TipoClase>();
		
		for(TipoClase idx : TipoClase.values()) {
			listTipoClases.add(idx);
		}
		return listTipoClases;
	}
	
//	// AEROPUERTOS  (GET)
//	{
//		"ciudad"
//		"pais"
//		"acronimo"
//	}
//	
//	
//	// AEROLINEAS (GET)
//	{
//		"nombreAerolinea"
//		"codigoAerolinea"
//		"porcentajeDescuentoMenores" // (POR AHORA A NIVEL AEROLINEA)
//	}
//	
//	// VUELO  (POST)
//	"vuelo"{
//		"codigo"
//		"fechaPartida"
//		"horaPartida"
//		"duracion"
//		"aeropuerto"
//		"aeropuertoDestino"
//		"asientosDisponiblesTotales"
//		"disponible" (BOOLEAN)
//		
//		"clases"{ 
//				[
//				  {
//					"tipoClase" (Economica , Premium Economy ,Ejecutiva, Primera)
//					"asientosDisponibles"
//					"precioClase"
//				  },
//				  {
//					"tipoClase" (Economica , Premium Economy ,Ejecutiva, Primera)
//					"asientosDisponibles"
//					"precioClase"
//				  }
//				]
//		}
//		
//	}
	
	
}