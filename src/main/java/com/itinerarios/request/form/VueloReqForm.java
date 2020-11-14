package com.itinerarios.request.form;

import com.itinerarios.dto.TipoClaseDTO;

public class VueloReqForm extends GeneralReqForm{

	private String fechaInicio;
	private String fechaFin;
	private String codigoAeropuertoOrigen;
	private String codigoAeropuertoDestino;
	private Long cantidadPasajerosAdultos;
	private Long cantidadPasajerosMenores;
	
	private TipoClaseDTO tipoClase;
	
	private String aerolineaCodigo;

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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

	public TipoClaseDTO getTipoClase() {
		return tipoClase;
	}

	public void setTipoClase(TipoClaseDTO tipoClase) {
		this.tipoClase = tipoClase;
	}

	public String getAerolineaCodigo() {
		return aerolineaCodigo;
	}

	public void setAerolineaCodigo(String aerolineaCodigo) {
		this.aerolineaCodigo = aerolineaCodigo;
	} 
}
