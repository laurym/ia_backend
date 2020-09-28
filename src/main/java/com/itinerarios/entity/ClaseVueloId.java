package com.itinerarios.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ClaseVueloId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tipoClase;
	
	private String codigoVuelo;
	
	public String getTipoClase() {
		return tipoClase;
	}

	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}
	
	public String getCodigoVuelo() {
		return codigoVuelo;
	}

	public void setCodigoVuelo(String codigoVuelo) {
		this.codigoVuelo = codigoVuelo;
	}
}
