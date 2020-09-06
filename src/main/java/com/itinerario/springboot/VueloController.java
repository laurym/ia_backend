package com.itinerario.springboot;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itinerarios.dto.VueloDTO;
import com.itinerarios.entity.Greeting;

@RestController
@RequestMapping("rest/vuelos")
public class VueloController {
	Logger LOG = LogManager.getLogger(VueloController.class);
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@PostMapping(
			  value = "/crearVuelos", consumes = "application/json", produces = "application/json")
	public Greeting crearVuelo(@RequestBody VueloDTO vuelo) {
		LOG.info("***** Inicio  greeting *****");
		LOG.info("***** Fin  greeting *****");
		return new Greeting(counter.incrementAndGet(), String.format(template, vuelo));
	}
}
