package com.itinerario.springboot;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itinerarios.entity.Greeting;

@RestController
@RequestMapping("rest")
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	Logger LOG = LogManager.getLogger(GreetingController.class);

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		LOG.info("***** Inicio  greeting *****");
		LOG.info("***** Fin  greeting *****");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}