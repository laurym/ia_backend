package com.itinerarios.dto;

import java.util.Date;
import java.util.List;

public class VueloDTO {
//	"vuelo"{
//		"codigo"
//		"fechaPartida"
//		"horaPartida"
//		"duracion"
//		"aeropuerto"
//		"aeropuertoDestino"
//		"asientosDisponiblesTotales"
//		"disponible" (BOOLEAN)

	private String codigo;
	private Date fechaPartida;
	private Date horaPartida;
	private Long duracion;
	private String codigoAeropuerto;
	private String codigoAeropuertoDestino;
//	private AeropuertoDTO aeropuerto;
//	private AeropuertoDTO aeropuertoDestino;
	private Long asientosDisponibles;
	private Boolean disponible;

	private List<ClaseVueloDTO> clases;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(Date fechaPartida) {
		this.fechaPartida = fechaPartida;
	}

	public Date getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(Date horaPartida) {
		this.horaPartida = horaPartida;
	}

	public Long getDuracion() {
		return duracion;
	}

	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}

	public String getCodigoAeropuerto() {
		return codigoAeropuerto;
	}

	public void setCodigoAeropuerto(String codigoAeropuerto) {
		this.codigoAeropuerto = codigoAeropuerto;
	}

	public String getCodigoAeropuertoDestino() {
		return codigoAeropuertoDestino;
	}

	public void setCodigoAeropuertoDestino(String codigoAeropuertoDestino) {
		this.codigoAeropuertoDestino = codigoAeropuertoDestino;
	}

//	public AeropuertoDTO getAeropuerto() {
//		return aeropuerto;
//	}
//
//	public void setAeropuerto(AeropuertoDTO aeropuerto) {
//		this.aeropuerto = aeropuerto;
//	}
//
//	public AeropuertoDTO getAeropuertoDestino() {
//		return aeropuertoDestino;
//	}
//
//	public void setAeropuertoDestino(AeropuertoDTO aeropuertoDestino) {
//		this.aeropuertoDestino = aeropuertoDestino;
//	}

	public Long getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(Long asientosDisponiblesTotales) {
		this.asientosDisponibles = asientosDisponiblesTotales;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public List<ClaseVueloDTO> getClases() {
		return clases;
	}

	public void setClases(List<ClaseVueloDTO> clases) {
		this.clases = clases;
	}
}
