package com.itinerarios.springboot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itinerarios.dto.VueloDTO;
import com.itinerarios.entity.Greeting;
import com.itinerarios.entity.Vuelo;
import com.itinerarios.request.form.VueloReqForm;
import com.itinerarios.springboot.repository.VueloRepository;
import com.itinerarios.springboot.utils.DTOUtils;

@RestController
@RequestMapping("rest/vuelos")
public class VueloController {
	Logger LOG = LogManager.getLogger(VueloController.class);
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired 
	private VueloRepository vueloRepository;
	
	
	@PostMapping(
			  value = "/crearVuelos", consumes = "application/json", produces = "application/json")
	public Greeting crearVuelo(@RequestBody VueloDTO vuelo) {
		LOG.info("***** Inicio  greeting *****");
		LOG.info("***** Fin  greeting *****");
		return new Greeting(counter.incrementAndGet(), String.format(template, vuelo));
	}
	
	@GetMapping("/vuelos")
	public List<VueloDTO> obtenerVuelos(@RequestBody VueloReqForm vueloReqForm) {
		LOG.info("***** Inicio  obtenerAeropuertos *****");
		Iterable<Vuelo> itObj = vueloRepository.findAll();
		Iterator<Vuelo> itObjs = itObj.iterator();
		
		List<VueloDTO> listDTO = new ArrayList<VueloDTO>();
		
		while (itObjs.hasNext()) {
			Vuelo vuelo = itObjs.next();
			VueloDTO dto = DTOUtils.convertToDto(vuelo);
			listDTO.add(dto);
		}
		
		LOG.info("***** Fin  obtenerAeropuertos *****");
	    return listDTO;
	}
}
