package com.itinerarios.springboot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itinerarios.dto.AeropuertoDTO;
import com.itinerarios.dto.ClaseVueloDTO;
import com.itinerarios.dto.VueloDTO;
import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.Vuelo;
import com.itinerarios.exceptions.ExceptionServiceGeneral;
import com.itinerarios.request.form.VueloReqCrearForm;
import com.itinerarios.request.form.VueloReqForm;
import com.itinerarios.response.form.GeneralResponseForm;
import com.itinerarios.response.form.VueloResponseForm;
import com.itinerarios.service.VueloServiceImpl;
import com.itinerarios.springboot.utils.DTOUtils;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("rest/vuelos")
public class VueloController {
	Logger LOG = LogManager.getLogger(VueloController.class);
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private  VueloServiceImpl vueloService;
	
	@PostMapping(
			  value = "/crearVuelo", consumes = "application/json", produces = "application/json")
	public GeneralResponseForm crearVuelo(@RequestBody VueloReqCrearForm vueloReqForm) {
		LOG.info("***** Inicio  crearVuelo *****");
	

		String mensajeError = "";
		GeneralResponseForm formResponse = null; 
		String pattern = "dd-MM-yyyy HH:mm:ss";
	    SimpleDateFormat formatter = new SimpleDateFormat(pattern);

//		DateTimeFormatter formatter = SimpleDateFormat.`ofPattern(pattern);
		
//		LocalDateTime fechaInicio;
		Date fechaInicio;
		AeropuertoDTO dtoOrigen = null;
		AeropuertoDTO dtoDestino = null;

		try {
			fechaInicio = formatter.parse(vueloReqForm.getFechaInicio() + " " + vueloReqForm.getHoraInicio());

		} catch (DateTimeParseException e) {
			// Thrown if text could not be parsed in the specified format
			formResponse = new GeneralResponseForm(
					"06 - ***** PARSE ERROR ***** Fecha u hora de inicio con error");
			throw new ExceptionServiceGeneral(formResponse.getMensaje());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			formResponse = new GeneralResponseForm(
					"06 - ***** PARSE ERROR ***** Fecha u hora de inicio con error");
			throw new ExceptionServiceGeneral(formResponse.getMensaje());
		}

		try {
			if (Long.valueOf(vueloReqForm.getDuracion()) < 0L) {
				formResponse = new GeneralResponseForm("07 - ***** PARSE ERROR ***** Duracion con error");
				throw new ExceptionServiceGeneral(formResponse.getMensaje());
			}
		} catch (NumberFormatException e) {
			formResponse = new GeneralResponseForm("07 - ***** PARSE ERROR ***** Duracion con error");
			throw new ExceptionServiceGeneral(formResponse.getMensaje());
		}

		try {
			if (vueloReqForm.getCodigoAeropuertoOrigen().compareTo(vueloReqForm.getCodigoAeropuertoDestino()) != 0) {
				Aeropuerto aeropuerto = vueloService.findByAcronimo(vueloReqForm.getCodigoAeropuertoOrigen());
				dtoOrigen = DTOUtils.convertToDto(aeropuerto);

				aeropuerto = vueloService.findByAcronimo(vueloReqForm.getCodigoAeropuertoDestino());
				dtoDestino = DTOUtils.convertToDto(aeropuerto);
			}

		} catch (RuntimeException e) {
			mensajeError = "04 -  ****** PARSE ERROR ****** aeropuertos con error - "
					+ vueloReqForm.getCodigoAeropuertoOrigen() + " - " + vueloReqForm.getCodigoAeropuertoDestino();
			throw new ExceptionServiceGeneral(mensajeError);
		}
		
		if(vueloReqForm.getClasesPorVueloList() == null || vueloReqForm.getClasesPorVueloList().isEmpty()) {
				mensajeError = "08 -  ****** PARSE ERROR ****** clases por vuelo con error";
				throw new ExceptionServiceGeneral(mensajeError);
		} else {
			for (ClaseVueloDTO idxDTO : vueloReqForm.getClasesPorVueloList()) {
				try {
					if (Long.valueOf(idxDTO.getAsientosClaseDisponibles()) < 0L) {
						formResponse = new GeneralResponseForm(
								"09 - ***** PARSE ERROR ***** asientosDisponibles con error");
						throw new ExceptionServiceGeneral(formResponse.getMensaje());
					}
				} catch (NumberFormatException e) {
					formResponse = new GeneralResponseForm(
							"09 - ***** PARSE ERROR ***** asientosDisponibles con error");
					throw new ExceptionServiceGeneral(formResponse.getMensaje());
				}
			}
		}
		VueloDTO vueloDTO = new VueloDTO();
		vueloDTO.setCodigo(vueloReqForm.getCodigo());
		vueloDTO.setAeropuerto(dtoOrigen);
		vueloDTO.setAeropuertoDestino(dtoDestino);
		vueloDTO.setFechaPartida(fechaInicio);//Date.from(fechaInicio.atZone(ZoneId.systemDefault()).toInstant()));
		vueloDTO.setDuracion(Long.valueOf(vueloReqForm.getDuracion()));
		vueloDTO.setDisponible(vueloReqForm.getIsDisponible());
		vueloDTO.setAsientosDisponibles(34L);
		Set<ClaseVueloDTO> vuelosSet = new HashSet<ClaseVueloDTO>(vueloReqForm.getClasesPorVueloList());

		vueloDTO.setClases(vuelosSet);

		vueloService.saveVuelo(DTOUtils.convertToEntity(vueloDTO));
		
		LOG.info("***** Fin  crearVuelo *****");
		if (mensajeError == null || mensajeError.isEmpty()) {
			mensajeError = "OK";
		}
		formResponse = new GeneralResponseForm(mensajeError);
		return formResponse;
	}
	
	@GetMapping("/busqueda")
	public List<VueloDTO> obtenerVuelos(@RequestBody VueloReqForm vueloReqForm) {
		LOG.info("***** Inicio  obtenerAeropuertos *****");
		VueloResponseForm vueloResponseForm = new VueloResponseForm();

		String pattern = "dd-MM-yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String mensajeError = null;
		
		/**
		 * OJO QUE LOS LOCALEDATE NO TIENEN HORA
		 * Hay que tener en cuenta el manejo de la fecha en la hora de busqueda o no 
		 * 
		 * eso queda a definir
		 */
		
		LocalDate fechaInicio;
		LocalDate fechaFin; 
		AeropuertoDTO dtoOrigen = null;
		AeropuertoDTO dtoDestino = null;
		
//		if (vueloReqForm.getFechaInicio().compareTo(vueloReqForm.getFechaFin()) != 0) {
			try {
				fechaInicio = LocalDate.parse(vueloReqForm.getFechaInicio(), formatter);

			} catch (DateTimeParseException e) {
				// Thrown if text could not be parsed in the specified format
				mensajeError = "01 - ***** PARSE ERROR ***** Fecha de inicio con error";
				throw new ExceptionServiceGeneral(mensajeError);

			}

			try {
				fechaFin = LocalDate.parse(vueloReqForm.getFechaFin(), formatter);

			} catch (DateTimeParseException e) {
				// Thrown if text could not be parsed in the specified format
				mensajeError = "02 - ***** PARSE ERROR ***** Fecha de fin con error";
				throw new ExceptionServiceGeneral(mensajeError);
			}
//		} 
		
		try {
			if (vueloReqForm.getCodigoAeropuertoOrigen().compareTo(vueloReqForm.getCodigoAeropuertoDestino()) != 0) {
				Aeropuerto aeropuerto = vueloService.findByAcronimo(vueloReqForm.getCodigoAeropuertoOrigen());
			
				dtoOrigen = DTOUtils.convertToDto(aeropuerto);
				
				aeropuerto = vueloService.findByAcronimo(vueloReqForm.getCodigoAeropuertoDestino());
				dtoDestino = DTOUtils.convertToDto(aeropuerto);
			}
				
		} catch(RuntimeException e) {
			mensajeError = "04 -  ****** PARSE ERROR ****** aeropuertos con error - " + vueloReqForm.getCodigoAeropuertoOrigen() + " - " + vueloReqForm.getCodigoAeropuertoDestino();
			throw new ExceptionServiceGeneral(mensajeError);
		}
	
		
		
		Iterable<Vuelo> itObj = vueloService.findAllVuelos();
		Iterator<Vuelo> itObjs = itObj.iterator();
		
		List<VueloDTO> listDTO = new ArrayList<VueloDTO>();
		
		while (itObjs.hasNext()) {
			Vuelo vuelo = itObjs.next();
			VueloDTO dto = DTOUtils.convertToDto(vuelo);
			listDTO.add(dto);
		}
		
		LOG.info("***** Fin  obtenerAeropuertos *****");
		if(mensajeError==null || mensajeError.isEmpty())
			mensajeError = "OK";
			vueloResponseForm.setListVuelos(listDTO);
			
			vueloResponseForm.setMensaje(new GeneralResponseForm(mensajeError));
		return listDTO;
	}
	
	
}
