package com.itinerarios.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="vuelo")
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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String codigo;
	private Date fechaPartida;
	
	@Transient
	private Date horaPartida;
	private Long duracion;
	
	@OneToOne
    @JoinColumn(name = "aeropuerto", referencedColumnName = "id", nullable = false)
	private Aeropuerto aeropuerto;

	
	@OneToOne
    @JoinColumn(name = "aeropuertoDestino", referencedColumnName = "id", nullable = false)
	private Aeropuerto aeropuertoDestino;
	private Long asientosDisponibles;
	private Boolean disponible;

//	@Transient
	 @OneToMany( mappedBy = "codigoVuelo")
	 @JsonIgnore
//	 @JsonManagedReference
//	    @JoinColumn(name = "codigo", referencedColumnName = "codigoVuelo", nullable = false)
	private Set<ClaseVuelo> clases;

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

	public Aeropuerto getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(Aeropuerto aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	public Aeropuerto getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
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

	public Set<ClaseVuelo> getClases() {
		return clases;
	}

	public void setClases(Set<ClaseVuelo> clases) {
		this.clases = clases;
	}
}
