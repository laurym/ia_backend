package com.itinerarios.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
//@Table(name="aerolinea")
public class Aerolinea{// implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// AEROLINEAS (GET)
//		{
//			"nombreAerolinea"
//			"codigoAerolinea"
//			"porcentajeDescuentoMenores" // (POR AHORA A NIVEL AEROLINEA)
//		}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombreAerolinea;
	
	private String codigoAerolinea;
	
	private Long porcentajeDescuentoMenores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreAerolinea() {
		return nombreAerolinea;
	}

	public void setNombreAerolinea(String nombreAerolinea) {
		this.nombreAerolinea = nombreAerolinea;
	}

	public String getCodigoAerolinea() {
		return codigoAerolinea;
	}

	public void setCodigoAerolinea(String codigoAerolinea) {
		this.codigoAerolinea = codigoAerolinea;
	}

	public Long getPorcentajeDescuentoMenores() {
		return porcentajeDescuentoMenores;
	}

	public void setPorcentajeDescuentoMenores(Long porcentajeDescuentoMenores) {
		this.porcentajeDescuentoMenores = porcentajeDescuentoMenores;
	}

}
