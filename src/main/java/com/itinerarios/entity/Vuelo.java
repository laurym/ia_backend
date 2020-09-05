package com.itinerarios.entity;

import java.util.Date;
import java.util.List;

public class Vuelo {
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
	private String aeropuerto;
//	private String aeropuertoDestino;
//	
//	private Aeropuerto aeropuerto;
	private Aeropuerto aeropuertoDestino;
	private Long asientosDisponiblesTotales;
	private Boolean disponible;

	private List<ClaseVuelo> clases;

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

//	public Aeropuerto getAeropuerto() {
//		return aeropuerto;
//	}
//
//	public void setAeropuerto(Aeropuerto aeropuerto) {
//		this.aeropuerto = aeropuerto;
//	}

	public String getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(String aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	public Aeropuerto getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
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

	public List<ClaseVuelo> getClases() {
		return clases;
	}

	public void setClases(List<ClaseVuelo> clases) {
		this.clases = clases;
	}
}
