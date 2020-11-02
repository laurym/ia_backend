package com.itinerarios.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="vuelo")
public class Vuelo {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private String codigo;
	private String fechaPartida;
	
	private String horaPartida;
	private Long duracion;
	private Long asientosVendidos;
	
	@OneToOne
    @JoinColumn(name = "aeropuerto", referencedColumnName = "id", nullable = false)
	private Aeropuerto aeropuerto;

	
	@OneToOne
    @JoinColumn(name = "aeropuertoDestino", referencedColumnName = "id", nullable = false)
	private Aeropuerto aeropuertoDestino;
	private Long asientosDisponibles;
	private Boolean disponible;

	@OneToMany( mappedBy = "codigoVuelo")// , cascade = CascadeType.ALL)
	private Set<ClaseVuelo> clases;
	
	@OneToOne
    @JoinColumn(name = "aerolinea", referencedColumnName = "id", nullable = false)
	private Aerolinea aerolinea;

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

	public Long getDuracion() {
		return duracion;
	}

	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}

	public Long getAsientosVendidos() {
		return asientosVendidos;
	}

	public void setAsientosVendidos(Long asientosVendidos) {
		this.asientosVendidos = asientosVendidos;
	}

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

	public Aerolinea getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
}
