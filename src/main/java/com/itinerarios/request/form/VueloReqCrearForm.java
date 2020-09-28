package com.itinerarios.request.form;

import java.util.List;

import com.itinerarios.dto.ClaseVueloDTO;
import com.itinerarios.dto.RecurrenciaVueloDTO;

public class VueloReqCrearForm extends GeneralReqForm {

	private String codigo;
	private String fechaInicio;
	private String horaInicio;
	private String duracion;

	private String codigoAeropuertoOrigen;
	private String codigoAeropuertoDestino;
	
	private List<ClaseVueloDTO> clasesPorVueloList;
	private Boolean isDisponible;
	
	private RecurrenciaVueloDTO recurrencia;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

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

	public List<ClaseVueloDTO> getClasesPorVueloList() {
		return clasesPorVueloList;
	}

	public void setClasesPorVueloList(List<ClaseVueloDTO> clasesPorVueloList) {
		this.clasesPorVueloList = clasesPorVueloList;
	}

	public Boolean getIsDisponible() {
		return isDisponible;
	}

	public void setIsDisponible(Boolean isDisponible) {
		this.isDisponible = isDisponible;
	}

	public RecurrenciaVueloDTO getRecurrencia() {
		return recurrencia;
	}

	public void setRecurrencia(RecurrenciaVueloDTO recurrencia) {
		this.recurrencia = recurrencia;
	}

}
