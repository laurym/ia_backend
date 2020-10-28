package com.itinerarios.springboot;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.itinerarios.dto.AerolineaDTO;
import com.itinerarios.dto.AeropuertoDTO;
import com.itinerarios.dto.ClaseVueloDTO;
import com.itinerarios.dto.RecurrenciaVueloDTO;
import com.itinerarios.dto.TipoClaseDTO;
import com.itinerarios.dto.VueloDTO;
import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.Vuelo;
import com.itinerarios.enums.TipoRecurrencia;
import com.itinerarios.exceptions.ExceptionServiceGeneral;
import com.itinerarios.request.form.ClaseVueloFormDTO;
import com.itinerarios.request.form.UsuarioReqInfoForm;
import com.itinerarios.request.form.VueloReqConfirmarForm;
import com.itinerarios.request.form.VueloReqCrearForm;
import com.itinerarios.request.form.VueloReqForm;
import com.itinerarios.response.form.GeneralResponseForm;
import com.itinerarios.response.form.VueloResponseForm;
import com.itinerarios.service.VueloServiceImpl;
import com.itinerarios.springboot.utils.ConstantsUtil;
import com.itinerarios.springboot.utils.DTOUtils;
import com.itinerarios.springboot.utils.UsuarioUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("vuelos")
@Api(tags  = "vuelosAPI")
public class VueloController {
	Logger LOG = LogManager.getLogger(VueloController.class);
	
	@Autowired
	private VueloServiceImpl vueloService;
	
	@PostMapping(value = "/crearVuelo", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Método para crear los vuelos según los parametros ingresados y el usuario de token informado", 
				  notes = "En el caso de error se muestra el mensaje correspondiente de error al ingreso de datos."
						  + "\n\n	Se retorna el mensaje de OK ante un alta saitsfactorio.")
		
	public GeneralResponseForm crearVuelo(@RequestBody VueloReqCrearForm vueloReqForm,
			@RequestHeader("token") String token) throws JsonMappingException, JsonProcessingException, IOException {
		
		LOG.info("***** Inicio  crearVuelo *****");
		
		UsuarioReqInfoForm usuario = UsuarioUtils.getUsuario(token);
		vueloReqForm.setAerolineaCodigo(usuario.getPropiedades().get("aerolinea"));

		String mensajeError = "";
		GeneralResponseForm formResponse = null;
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtil.FORMAT_FECHA_CON_HORA);

		Date fechaInicio;
		AeropuertoDTO dtoOrigen = null;
		AeropuertoDTO dtoDestino = null;
		AerolineaDTO aerolineaDTO = null;
		
		try {
			fechaInicio = formatter.parse(vueloReqForm.getFechaInicio() + " " + vueloReqForm.getHoraInicio());
			Date fechaAComparar = new Date(Instant.now().toEpochMilli());
			
			if(fechaAComparar.compareTo(fechaInicio) > 0) {
				formResponse = new GeneralResponseForm("10 - ***** PARSE ERROR ***** La fecha tiene que ser mayor a la actual");
				throw new ExceptionServiceGeneral(formResponse.getMensaje());
			}

		} catch (DateTimeParseException e) {
			// Thrown if text could not be parsed in the specified format
			formResponse = new GeneralResponseForm("06 - ***** PARSE ERROR ***** Fecha u hora de inicio con error");
			throw new ExceptionServiceGeneral(formResponse.getMensaje());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			formResponse = new GeneralResponseForm("06 - ***** PARSE ERROR ***** Fecha u hora de inicio con error");
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
				Aeropuerto aeropuerto = vueloService.buscarPorAcronimo(vueloReqForm.getCodigoAeropuertoOrigen());
				dtoOrigen = DTOUtils.convertToDto(aeropuerto);

				aeropuerto = vueloService.buscarPorAcronimo(vueloReqForm.getCodigoAeropuertoDestino());
				dtoDestino = DTOUtils.convertToDto(aeropuerto);
			}

		} catch (RuntimeException e) {
			mensajeError = "04 -  ****** PARSE ERROR ****** aeropuertos con error - "
					+ vueloReqForm.getCodigoAeropuertoOrigen() + " - " + vueloReqForm.getCodigoAeropuertoDestino();
			throw new ExceptionServiceGeneral(mensajeError);
		}

		if (vueloReqForm.getClasesPorVueloList() == null || vueloReqForm.getClasesPorVueloList().isEmpty()) {
			mensajeError = "08 -  ****** PARSE ERROR ****** clases por vuelo con error";
			throw new ExceptionServiceGeneral(mensajeError);
		} else {
			for (ClaseVueloFormDTO idxDTO : vueloReqForm.getClasesPorVueloList()) {
				try {
					if (Long.valueOf(idxDTO.getAsientosClaseDisponibles()) < 0L) {
						formResponse = new GeneralResponseForm("09 - ***** PARSE ERROR ***** asientosDisponibles con error");
						throw new ExceptionServiceGeneral(formResponse.getMensaje());
					} 
				} catch (NumberFormatException e) {
					formResponse = new GeneralResponseForm("09 - ***** PARSE ERROR ***** asientosDisponibles con error");
					throw new ExceptionServiceGeneral(formResponse.getMensaje());
				}
				
				try {
					if (Double.valueOf(idxDTO.getPrecio()) < 0D) {
						formResponse = new GeneralResponseForm("09 - ***** PARSE ERROR ***** precio con error");
						throw new ExceptionServiceGeneral(formResponse.getMensaje());
					} 
				} catch (NumberFormatException e) {
					formResponse = new GeneralResponseForm("09 - ***** PARSE ERROR ***** precio con error");
					throw new ExceptionServiceGeneral(formResponse.getMensaje());
				}
			}
		}
		
		try {
			if (Long.valueOf(vueloReqForm.getPorcentajeDescuentoMenor()) < 0L) {
				formResponse = new GeneralResponseForm("12 - ***** DESCUENTO ERROR ***** Descuento menores con error");
				throw new ExceptionServiceGeneral(formResponse.getMensaje());
			}
		} catch (NumberFormatException e) {
			formResponse = new GeneralResponseForm("12 - ***** DESCUENTO ERROR ***** Descuento con error");
			throw new ExceptionServiceGeneral(formResponse.getMensaje());
		}
		
		if (vueloReqForm.getAerolineaCodigo() == null) {
			mensajeError = "11 -  ****** AEROLINEA ERROR ****** aerolinea con error";
			throw new ExceptionServiceGeneral(mensajeError);
		} else {
			aerolineaDTO = DTOUtils.convertToDto(vueloService.buscarAerolinea(vueloReqForm.getAerolineaCodigo()));

			if (aerolineaDTO == null) {
				mensajeError = "11 -  ****** AEROLINEA ERROR ****** aerolinea con error";
				throw new ExceptionServiceGeneral(mensajeError);
			}
			aerolineaDTO.setPorcentajeDescuentoMenores(Long.valueOf(vueloReqForm.getPorcentajeDescuentoMenor()));
		}
		
		
		if (vueloReqForm.getRecurrencia() == null ||
				(vueloReqForm.getRecurrencia() != null
					&& ((vueloReqForm.getRecurrencia().getTipoRecurrencia().compareTo(TipoRecurrencia.MENSUAL) == 0 && vueloReqForm.getRecurrencia().getCantidadRecurrencia() > 12)
						|| (vueloReqForm.getRecurrencia().getTipoRecurrencia().compareTo(TipoRecurrencia.SEMANAL) == 0 && vueloReqForm.getRecurrencia().getCantidadRecurrencia() > 52)))) {
			mensajeError = "12 -  ****** RECURRENCIA ERROR ****** recurrencia con error";
			throw new ExceptionServiceGeneral(mensajeError);
		}
		else if (vueloReqForm.getRecurrencia()==null){
			RecurrenciaVueloDTO recurrenciaDTO = new RecurrenciaVueloDTO();
			recurrenciaDTO.setTipoRecurrencia(TipoRecurrencia.UNICO);
			recurrenciaDTO.setCantidadRecurrencia(1L);
			
			vueloReqForm.setRecurrencia(recurrenciaDTO);
		}
		String codigoRecurrente = null;
		for (int i = 0; i < vueloReqForm.getRecurrencia().getCantidadRecurrencia(); i++) {
			VueloDTO vueloDTO = new VueloDTO();

			vueloDTO.setCodigo(getCodigoVuelo(vueloReqForm, aerolineaDTO, i, codigoRecurrente));
			System.out.println(i  + "  codigo Vuelo : " + vueloDTO.getCodigo());
			vueloDTO.setAeropuerto(dtoOrigen);
			vueloDTO.setAeropuertoDestino(dtoDestino);
			DateTimeFormatter formatterDT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			
			if (vueloReqForm.getRecurrencia().getTipoRecurrencia().compareTo(TipoRecurrencia.SEMANAL) == 0) {
				LocalDateTime today = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				LocalDateTime sigSemana = today.plus(i, ChronoUnit.WEEKS);
				vueloDTO.setFechaPartida(formatterDT.format(sigSemana).split(" ")[0].trim());
			
			} else if (vueloReqForm.getRecurrencia().getTipoRecurrencia().compareTo(TipoRecurrencia.MENSUAL) == 0) {
				LocalDateTime today = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				LocalDateTime sigMes = today.plus(i, ChronoUnit.MONTHS);
				vueloDTO.setFechaPartida(formatterDT.format(sigMes).split(" ")[0].trim());
			
			} else {
				vueloDTO.setFechaPartida(vueloReqForm.getFechaInicio());
			}
			vueloDTO.setHoraPartida(vueloReqForm.getHoraInicio());
			
			
			vueloDTO.setDuracion(Long.valueOf(vueloReqForm.getDuracion()));
			vueloDTO.setDisponible(Boolean.TRUE);// vueloReqForm.getIsDisponible());
			Set<ClaseVueloFormDTO> vuelosSet = new HashSet<ClaseVueloFormDTO>(vueloReqForm.getClasesPorVueloList());
			Set<ClaseVueloDTO> setVuelosDTO = new HashSet<ClaseVueloDTO>();

			Iterator<ClaseVueloFormDTO> itSet = vuelosSet.iterator();

			while (itSet.hasNext()) {
				ClaseVueloFormDTO claseDTO = itSet.next();
				if (claseDTO.getAsientosClaseDisponibles().compareTo(0L) != 0) {
					ClaseVueloDTO claseVueloDTO = new ClaseVueloDTO();
					TipoClaseDTO tipoDTO = new TipoClaseDTO();
					tipoDTO.setCodigoClase(claseDTO.getCodigoClase());
					claseVueloDTO.setClase(tipoDTO);
					claseVueloDTO.setAsientosClaseDisponibles(claseDTO.getAsientosClaseDisponibles());
					claseVueloDTO.setAsientosVendidos(0L);
					claseVueloDTO.setPrecio(claseDTO.getPrecio());
					setVuelosDTO.add(claseVueloDTO);
				}
			}

			vueloDTO.setClases(setVuelosDTO);
			Iterator<ClaseVueloDTO> iterator = vueloDTO.getClases().iterator();
			Long asientosDisponiblesTotal = 0L;
			while (iterator.hasNext()) {
				ClaseVueloDTO idxDTO = iterator.next();
				asientosDisponiblesTotal += idxDTO.getAsientosClaseDisponibles();
			}

			vueloDTO.setAsientosDisponibles(asientosDisponiblesTotal);
			vueloDTO.setAerolinea(aerolineaDTO);
			
			codigoRecurrente = vueloDTO.getCodigo();
//			vueloService.saveVuelo(DTOUtils.convertToEntity(vueloDTO));
		}
		LOG.info("***** Fin  crearVuelo *****");
		if (mensajeError == null || mensajeError.isEmpty()) {
			mensajeError = "OK";
		}
		formResponse = new GeneralResponseForm(mensajeError);
		return formResponse;
	}

	@PostMapping(value = "/confirmarVuelo", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Método para confirmar el vuelo seleccionado según los parametros ingresados y el usuario de token informado", 
				  notes = "En el caso de error se muestra el mensaje correspondiente de error al ingreso de datos."
						  + "\n\n	Se retorna el mensaje de \"OK\" ante una confirmacion exitosa."
						  + "\n\n	Se retorna el mensaje de \"No se pudo completar la operación.\" ante una confirmacion no exitosa.")
		
	public GeneralResponseForm confirmarVuelo(@RequestBody  VueloReqConfirmarForm vueloReqForm,
			@RequestHeader("token") String token) throws JsonMappingException, JsonProcessingException, IOException {
		
		LOG.info("***** Inicio  confirmarVuelo *****");
		
		UsuarioReqInfoForm usuario = UsuarioUtils.getUsuario(token);
		String mensajeError = "";
		GeneralResponseForm formResponse = null;
		VueloDTO vueloDTO = new VueloDTO();
		Long cantidadPasajeros = vueloReqForm.getCantidadPasajeros();
		Boolean encontrado = Boolean.FALSE;
		try {
			if (Long.valueOf(cantidadPasajeros) < 0L) {
				formResponse = new GeneralResponseForm("07 - ***** PARSE ERROR ***** cantidadPasajeros con error");
				throw new ExceptionServiceGeneral(formResponse.getMensaje());
			}
		} catch (NumberFormatException e) {
			formResponse = new GeneralResponseForm("07 - ***** PARSE ERROR ***** cantidadPasajeros con error");
			throw new ExceptionServiceGeneral(formResponse.getMensaje());
		}

		try {
				Vuelo vuelo = vueloService.buscarVueloPorCodigo(vueloReqForm.getCodigoVuelo());
				vueloDTO = DTOUtils.convertToDto(vuelo);
		} catch (RuntimeException e) {
			mensajeError = "04 -  ****** PARSE ERROR ****** aeropuertos con error - " + vueloReqForm.getCodigoVuelo();
			throw new ExceptionServiceGeneral(mensajeError);
		}

		Iterator <ClaseVueloDTO> iterator = vueloDTO.getClases().iterator();
		String codigoClase = vueloReqForm.getCodigoClase();
		Long valorProbable = 0L;
		while (iterator.hasNext()) {
			ClaseVueloDTO idxDTO = iterator.next();
			valorProbable = idxDTO.getAsientosVendidos() + cantidadPasajeros;
			if(codigoClase.compareTo(idxDTO.getClase().getCodigoClase()) ==0
				&& (valorProbable.compareTo(idxDTO.getAsientosClaseDisponibles()) >=0  )) {
				encontrado = Boolean.TRUE;
				idxDTO.setAsientosVendidos(valorProbable);
				break;
			}
		}
		
		if (encontrado) {
			vueloService.saveVuelo(DTOUtils.convertToEntity(vueloDTO));
		}	else {
			mensajeError = "No se pudo completar la operación.";
		}

		LOG.info("***** Fin  confirmarVuelo *****");
		if (mensajeError == null || mensajeError.isEmpty()) {
			mensajeError = "OK";
		}
		formResponse = new GeneralResponseForm(mensajeError);
		return formResponse;
	}
		
	@GetMapping("/busqueda")
	@ApiOperation(value = "Método para realizar la búsqueda de vuelos.",
	 					 
				notes= "\n\n Ante un error en la respuesta en los datos de entrada se retorna el error 409."
						+ "\n\n Los parámetros a enviar son los siguientes :"
						 + " \n\n  codigoAeropuertoOrigen (obligatorio), codigoAreopuertoDestino (obligatorio), "
						 + " \n\n  fechaInicio tiene que tener el formato dd/MM/YYYY, "
						 + " \n\n  cantidadPasajerosAdultos, cantidadPasajerosMenores, codigoClase"
						+ "  \n\n   **************************************************************  "
						 + " \n\n  Ejemplo : URLBASE/itinerarios/rest/vuelos/busqueda?codigoAeropuertoDestino=EZE&codigoAeropuertoOrigen=FCO&codigoClase=C&fechaInicio=24/10/2020&cantidadPasajerosAdultos=2&cantidadPasajerosMenores=1")
	public List<VueloDTO> obtenerVuelos(@RequestParam(name="codigoAeropuertoOrigen", required = true) String codigoAeropuertoOrigen,
										@RequestParam(name="codigoAeropuertoDestino", required = true) String codigoAeropuertoDestino,
										@RequestParam Map<String,String> vueloReqMap){//@RequestParam VueloReqForm vueloReqForm) {
		LOG.info("***** Inicio  obtenerAeropuertos *****");
		VueloResponseForm vueloResponseForm = new VueloResponseForm();
		
		
		String mensajeError = "";
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtil.FORMAT_FECHA);

		@SuppressWarnings("unused")
		Date travelDate = null;
		VueloReqForm vueloReqForm = null;
		List<VueloDTO> listDTO = new ArrayList<VueloDTO>();
		try {
				  vueloReqForm = new VueloReqForm();
				  vueloReqForm.setCodigoAeropuertoOrigen(codigoAeropuertoOrigen);
				  vueloReqForm.setCodigoAeropuertoDestino(codigoAeropuertoDestino);
				  
				  vueloReqForm.setFechaInicio(vueloReqMap.get(ConstantsUtil.MAP_KEY_FECHA_INICIO));
				  TipoClaseDTO tipoClaseDTO = new TipoClaseDTO();
				  tipoClaseDTO.setCodigoClase(vueloReqMap.get(ConstantsUtil.MAP_KEY_CODIGO_CLASE));
				  vueloReqForm.setTipoClase(tipoClaseDTO);
				  try {
				  vueloReqForm.setCantidadPasajerosAdultos(vueloReqMap.get(ConstantsUtil.MAP_KEY_CANT_PASAJEROS_ADULTOS) == null? 0 : Long.valueOf(vueloReqMap.get(ConstantsUtil.MAP_KEY_CANT_PASAJEROS_ADULTOS)));
				  } catch (NumberFormatException e) {
					  mensajeError = "02 - ***** PARSE ERROR ***** Cantidad de pasajeros adultos con error";
						throw new ExceptionServiceGeneral(mensajeError);
				  }
				  
				  try {
				  vueloReqForm.setCantidadPasajerosMenores(vueloReqMap.get(ConstantsUtil.MAP_KEY_CANT_PASAJEROS_MENORES) == null? 0 : Long.valueOf(vueloReqMap.get(ConstantsUtil.MAP_KEY_CANT_PASAJEROS_MENORES)));
				  }  catch (NumberFormatException e) {
					  mensajeError = "02 - ***** PARSE ERROR ***** Cantidad de pasajeros adultos con error";
						throw new ExceptionServiceGeneral(mensajeError);
				  }
			  
			  
			if (vueloReqForm.getFechaInicio() != null) {
				try {
					travelDate = formatter.parse(vueloReqForm.getFechaInicio());
				} catch (DateTimeParseException e) {
					// Thrown if text could not be parsed in the specified format
					mensajeError = "01 - ***** PARSE ERROR ***** Fecha de inicio con error";
					throw new ExceptionServiceGeneral(mensajeError);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					mensajeError = "01 - ***** PARSE ERROR ***** Fecha de inicio con error";
					throw new ExceptionServiceGeneral(mensajeError);
				}
			}
			
			if (vueloReqForm.getCantidadPasajerosAdultos() < 0 || vueloReqForm.getCantidadPasajerosMenores() < 0) {
				mensajeError = "13 -  ****** BUSQUEDA ERROR ****** cantidad pasajeros con error";
				throw new ExceptionServiceGeneral(mensajeError);
			}

			if (vueloReqForm.getCantidadPasajerosAdultos() == 0 && vueloReqForm.getCantidadPasajerosMenores() == 0) {
				vueloReqForm.setCantidadPasajerosAdultos(ConstantsUtil.CANTIDAD_PASAJEROS_DEFAULT);
			}

			if (vueloReqForm.getTipoClase() == null
					|| vueloReqForm.getTipoClase().getCodigoClase() == null
					|| vueloReqForm.getTipoClase().getCodigoClase().isEmpty()) {
				TipoClaseDTO tipoClase = new TipoClaseDTO();
				tipoClase.setCodigoClase(ConstantsUtil.CODIGO_CLASE_DEFAULT);
				vueloReqForm.setTipoClase(tipoClase);
			}

			Long cantidadPasajeros = vueloReqForm.getCantidadPasajerosAdultos() + vueloReqForm.getCantidadPasajerosMenores();

			String codigoClase = vueloReqForm.getTipoClase() == null ? null : vueloReqForm.getTipoClase().getCodigoClase();

			Iterable<Vuelo> itObj = vueloService.buscarVuelosDisponibles(
												 vueloReqForm.getCodigoAeropuertoOrigen().toUpperCase(), vueloReqForm.getCodigoAeropuertoDestino(),
												 cantidadPasajeros, vueloReqForm.getFechaInicio(), codigoClase);
			Iterator<Vuelo> itObjs = itObj.iterator();

			Double valorTotal = 0D;
			while (itObjs.hasNext()) {
				Vuelo vuelo = itObjs.next();
				VueloDTO dto = DTOUtils.convertToDto(vuelo);
				Iterator<ClaseVueloDTO> clasesSet = dto.getClases().iterator();
				Double precio = 0D;
				while (clasesSet.hasNext()) {
					ClaseVueloDTO claseIdx = clasesSet.next();
					precio = claseIdx.getPrecio();
				}
				valorTotal = precio
								* (vueloReqForm.getCantidadPasajerosAdultos() + vueloReqForm.getCantidadPasajerosMenores()
								* (Double.valueOf(dto.getAerolinea().getPorcentajeDescuentoMenores()) / 100D));
				dto.setValorTotal(valorTotal);
				SimpleDateFormat formatter2 = new SimpleDateFormat(ConstantsUtil.FORMAT_FECHA_CON_HORA);
				Date fechaInicio = formatter2.parse(dto.getFechaPartida() + " " + dto.getHoraPartida());
				Date fechaLlegada = new Date (fechaInicio.getTime() + vuelo.getDuracion() * ConstantsUtil.MULTIPLIER_MINUTE);
				
				String date = formatter2.format(fechaLlegada);
				String [] arrayDate = date.split(" ");
				
				dto.setFechaLlegada(arrayDate[0]);
				dto.setHoraLlegada(arrayDate[1]);
				
				listDTO.add(dto);
			}

			LOG.info("***** Fin  obtenerAeropuertos *****");
			if (mensajeError == null || mensajeError.isEmpty())
				mensajeError = "OK";
			vueloResponseForm.setListVuelos(listDTO);

			vueloResponseForm.setMensaje(new GeneralResponseForm(mensajeError));
			return listDTO;
		} catch (RuntimeException e) {
			mensajeError = "13 - ***** BUSQUEDA ERROR ***** Sistema error";
			throw new ExceptionServiceGeneral(mensajeError);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensajeError = "13 - ***** BUSQUEDA ERROR ***** Sistema error";
			throw new ExceptionServiceGeneral(mensajeError);
		}
	}
	
	private Long getRandomNumberLongs(Long min, Long max) {
		Random random = new Random();
	    return random.longs(min,(max+1)).findFirst().getAsLong();
	}
	
	private String getCodigoVuelo(VueloReqCrearForm vueloReqForm, AerolineaDTO aerolineaDTO, Integer j,
			String codigoVuelo) {

		String valorCodigo = "";
		if (vueloReqForm.getRecurrencia().getTipoRecurrencia().compareTo(TipoRecurrencia.UNICO) == 0) {
			valorCodigo = getCodigoVuelo(vueloReqForm, aerolineaDTO);
		} else {
			if (j <= ConstantsUtil.repeticiones.length) {
				valorCodigo = codigoVuelo != null ? codigoVuelo : getCodigoVuelo(vueloReqForm, aerolineaDTO);
				if (j >= 2) {
					if (valorCodigo.lastIndexOf(ConstantsUtil.repeticiones[j - 2]) > 0)
						valorCodigo = valorCodigo.substring(0,
								valorCodigo.lastIndexOf(ConstantsUtil.repeticiones[j - 2]))
								+ ConstantsUtil.repeticiones[j - 1];
				} else if (j == 1) {
					valorCodigo = valorCodigo + ConstantsUtil.repeticiones[j - 1];
				}
			} else {
				Long valueModDiv = Math.floorDiv(Long.valueOf(j), 24L);
				if (j == ConstantsUtil.repeticiones.length + 1) {
					valorCodigo = codigoVuelo != null ? codigoVuelo.substring(0, codigoVuelo.length() - 1) : getCodigoVuelo(vueloReqForm, aerolineaDTO);
					valorCodigo = valorCodigo + ConstantsUtil.repeticiones[j - (ConstantsUtil.repeticiones.length + 1)] + ConstantsUtil.repeticiones[valueModDiv.intValue()];
				} else {
					valorCodigo = codigoVuelo != null ? codigoVuelo.substring(0, codigoVuelo.length() - 1) : getCodigoVuelo(vueloReqForm, aerolineaDTO);
					valorCodigo = valorCodigo + ConstantsUtil.repeticiones[j - ConstantsUtil.repeticiones.length];

				}
			}
		}
		return valorCodigo;
	}

	private String getCodigoVuelo(VueloReqCrearForm vueloReqForm, AerolineaDTO aerolineaDTO) {
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantsUtil.FORMAT_FECHA);
		String randomString = "";
		try {
			randomString = getRandomNumberLongs(Long.valueOf(vueloReqForm.getDuracion()) , ((Date) formatter.parse(vueloReqForm.getFechaInicio() + " " + vueloReqForm.getHoraInicio())).getTime()).toString();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String codigoVuelo = aerolineaDTO.getCodigoAerolinea() + " " + randomString.substring(0,6);
		Vuelo vueloBase = vueloService.buscarVueloPorCodigo(codigoVuelo);
		Boolean encontrado = Boolean.TRUE;
		if (vueloBase != null) {
			for (int i = 1; i <= 8; i++) {
				if (vueloBase.getCodigo().compareTo(codigoVuelo) == 0) {
					randomString = Long.valueOf(Long.valueOf(randomString) + i).toString();
					codigoVuelo = aerolineaDTO.getCodigoAerolinea() + " " + randomString.substring(0, 7);
					vueloBase = vueloService.buscarVueloPorCodigo(codigoVuelo);
				} else {
					encontrado = Boolean.FALSE;
					break;
				}
			}
		}
		return codigoVuelo;
	}
}
