package com.itinerarios.dto;

import java.util.Set;


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
	private String fechaPartida;
	private String horaPartida;
	private String fechaLlegada;
	private String horaLlegada;
	private Long duracion;
	private AeropuertoDTO aeropuerto;
	private AeropuertoDTO aeropuertoDestino;
	private Long asientosDisponibles;
	private Boolean disponible;

	private Set<ClaseVueloDTO> clases;
	private AerolineaDTO aerolinea;
	
	private Double valorTotal;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(String fechaPartida) {
		this.fechaPartida = fechaPartida;
	}

	public String getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}

	public String getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(String fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public String getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public Long getDuracion() {
		return duracion;
	}

	/**
	 * 
	 * @param duracion la duracion es en minutos
	 */
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

	public Set<ClaseVueloDTO> getClases() {
		return clases;
	}

	public void setClases(Set<ClaseVueloDTO> clases) {
		this.clases = clases;
	}

	public AerolineaDTO getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(AerolineaDTO aerolinea) {
		this.aerolinea = aerolinea;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
