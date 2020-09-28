package com.itinerarios.request.form;

import java.util.List;

import com.itinerarios.dto.ClaseVueloDTO;
import com.itinerarios.entity.RecurrenciaVuelo;

public class VueloReqCrearForm extends GeneralReqForm {

	private String fechaInicio;
	private String horaInicio;
	private String duracion;

	private String codigoAeropuertoOrigen;
	private String codigoAeropuertoDestino;
	
	private Long cantidadPasajerosAdultos;
	private Long cantidadPasajerosMenores;

	private List<ClaseVueloDTO> clasesPorVueloList;
	
	
	private RecurrenciaVuelo recurrencia;
	
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getDuracion() {
		return duracion;
	}

	/**
	 * 
	 * @param duracion debe estar expresado en horas de duracion
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getCodigoAeropuertoOrigen() {
		return codigoAeropuertoOrigen;
	}

	public void setCodigoAeropuertoOrigen(String codigoAeropuertoOrigen) {
		this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
	}

	public String getCodigoAeropuertoDestino() {
		return codigoAeropuertoDestino;
	}

	public void setCodigoAeropuertoDestino(String codigoAeropuertoDestino) {
		this.codigoAeropuertoDestino = codigoAeropuertoDestino;
	}

	public Long getCantidadPasajerosAdultos() {
		return cantidadPasajerosAdultos;
	}

	public void setCantidadPasajerosAdultos(Long cantidadPasajerosAdultos) {
		this.cantidadPasajerosAdultos = cantidadPasajerosAdultos;
	}

	public Long getCantidadPasajerosMenores() {
		return cantidadPasajerosMenores;
	}

	public void setCantidadPasajerosMenores(Long cantidadPasajerosMenores) {
		this.cantidadPasajerosMenores = cantidadPasajerosMenores;
	}

	public List<ClaseVueloDTO> getClasesPorVueloList() {
		return clasesPorVueloList;
	}

	public void setClasesPorVueloList(List<ClaseVueloDTO> clasesPorVueloList) {
		this.clasesPorVueloList = clasesPorVueloList;
	}

	public RecurrenciaVuelo getRecurrencia() {
		return recurrencia;
	}

	public void setRecurrencia(RecurrenciaVuelo recurrencia) {
		this.recurrencia = recurrencia;
	}

}
