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
	private AeropuertoDTO aeropuerto;
	private AeropuertoDTO aeropuertoDestino;
	private Long asientosDisponiblesTotales;
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

	public AeropuertoDTO getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(AeropuertoDTO aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	public AeropuertoDTO getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(AeropuertoDTO aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}

	public Long getAsientosDisponiblesTotales() {
		return asientosDisponiblesTotales;
	}

	public void setAsientosDisponiblesTotales(Long asientosDisponiblesTotales) {
		this.asientosDisponiblesTotales = asientosDisponiblesTotales;
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
