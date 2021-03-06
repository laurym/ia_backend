package com.itinerarios.springboot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itinerarios.dto.AerolineaDTO;
import com.itinerarios.dto.AeropuertoDTO;
import com.itinerarios.dto.TipoClaseDTO;
import com.itinerarios.entity.Aerolinea;
import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.TipoClase;
import com.itinerarios.service.BaseServiceImpl;
import com.itinerarios.springboot.utils.DTOUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value= "")
@Api(tags  = "baseAPI")
public class BaseController {

	@Autowired 
	private BaseServiceImpl baseService;
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	Logger LOG = LogManager.getLogger(BaseController.class);
	
	@GetMapping("/aeropuertos")
	@ApiOperation(value = "Método para obtener los areopuertos", notes = "Retorna todos los aeropuertos disponibles")
	public List<AeropuertoDTO> obtenerAeropuertos() {
		LOG.info("***** Inicio  obtenerAeropuertos *****");
		Iterable<Aeropuerto> itObj = baseService.findAllAeropuertos();
		Iterator<Aeropuerto> itObjs = itObj.iterator();
		
		List<AeropuertoDTO> listDTO = new ArrayList<AeropuertoDTO>();
		
		while (itObjs.hasNext()) {
			Aeropuerto aeropuerto = itObjs.next();
			AeropuertoDTO dto = DTOUtils.convertToDto(aeropuerto);
			listDTO.add(dto);
		}
		
		LOG.info("***** Fin  obtenerAeropuertos *****");
	    return listDTO;
	}
	
	
	@GetMapping("/aerolineas" )
	@ApiOperation(value = "Método para obtener las aerolíneas", notes = "Retorna todas las aerolíneas disponibles")
	public @ResponseBody List<AerolineaDTO> obtenerAerolineas() {
		LOG.info("***** Inicio  obtenerAerolineas *****");
	    // This returns a JSON or XML with the users
			Iterable<Aerolinea> itAerolinea = baseService.findAllAerolineas();
			Iterator<Aerolinea> itObjs = itAerolinea.iterator();
			
			List<AerolineaDTO> listDTO = new ArrayList<AerolineaDTO>();
			
			while (itObjs.hasNext()) {
				Aerolinea aerolinea = itObjs.next();
				AerolineaDTO dto = DTOUtils.convertToDto(aerolinea);
				listDTO.add(dto);
			}
			
			LOG.info("***** Fin  obtenererolineas *****");
		    return listDTO;
	}
	
	@PostMapping(value = "/guardarAerolineas", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "Método para guardar la aerolínea", notes = "Retorna la aerolínea")
	public AerolineaDTO guardarAerolineas(@RequestBody AerolineaDTO aerolineaDTO) {
		LOG.info("***** Inicio  guardarAerolineas *****");
		// user here is a prepopulated User instance
		Aerolinea entity = DTOUtils.convertToEntity(aerolineaDTO);
	    // This returns a JSON or XML with the users
			entity = baseService.save(entity);
			
			LOG.info("***** Fin  guardarAerolineas *****");
		    return DTOUtils.convertToDto(entity);
	}
	
	@GetMapping("/clases")
	@ApiOperation(value = "Método para obtener las clases de vuelos", notes = "Retorna todas las clases disponibles")
	public List<TipoClaseDTO> obtenerClases(){ 
		LOG.info("***** Inicio  obtenerClases *****");
		Iterable<TipoClase> itObj = baseService.findAllTiposClase();
		Iterator<TipoClase> itObjs = itObj.iterator();
		
		List<TipoClaseDTO> listDTO = new ArrayList<TipoClaseDTO>();
		
		while (itObjs.hasNext()) {
			TipoClase tipoClase = itObjs.next();
			TipoClaseDTO dto = DTOUtils.convertToDto(tipoClase);
			listDTO.add(dto);
		}
		
		LOG.info("***** Fin  obtenerClases *****");
	    return listDTO;
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