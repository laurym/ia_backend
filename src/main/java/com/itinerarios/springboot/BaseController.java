package com.itinerarios.springboot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itinerarios.dto.AerolineaDTO;
import com.itinerarios.dto.AeropuertoDTO;
import com.itinerarios.entity.Aerolinea;
import com.itinerarios.entity.Greeting;
import com.itinerarios.enums.TipoClase;
import com.itinerarios.springboot.repository.AerolineaRepository;
import com.itinerarios.springboot.utils.DTOUtils;



@RestController
@RequestMapping("rest")
public class BaseController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	Logger LOG = LogManager.getLogger(BaseController.class);
	
	@Autowired 
	private AerolineaRepository aerolineaRepository; 

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
	public @ResponseBody List<AerolineaDTO> obtenerAerolineas() {
		LOG.info("***** Inicio  obtenerAeropuertos *****");
	    // This returns a JSON or XML with the users
			Iterable<Aerolinea> itAerolinea = aerolineaRepository.findAll();
			Iterator<Aerolinea> itObjs = itAerolinea.iterator();
			
			List<AerolineaDTO> listDTO = new ArrayList<AerolineaDTO>();
			
			while (itObjs.hasNext()) {
				Aerolinea aerolinea = itObjs.next();
				AerolineaDTO dto = new AerolineaDTO();
				dto.setId(aerolinea.getId());
				dto.setCodigoAerolinea(aerolinea.getCodigoAerolinea());
				dto.setNombreAerolinea(aerolinea.getNombreAerolinea());
				dto.setPorcentajeDescuentoMenores(aerolinea.getPorcentajeDescuentoMenores());
				
				listDTO.add(dto);
			}
			
			LOG.info("***** Fin  obtenerAeropuertos *****");
		    return listDTO;
	}
	
	@PostMapping(value = "/aerolineas", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
	public AerolineaDTO guardarAerolineas(@RequestBody AerolineaDTO aerolineaDTO) {
		LOG.info("***** Inicio  obtenerAeropuertos *****");
		// user here is a prepopulated User instance
		Aerolinea entity = DTOUtils.convertToEntity(aerolineaDTO);
	    // This returns a JSON or XML with the users
			entity = aerolineaRepository.save(entity);
			
			LOG.info("***** Fin  obtenerAeropuertos *****");
		    return DTOUtils.convertToDto(entity);
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