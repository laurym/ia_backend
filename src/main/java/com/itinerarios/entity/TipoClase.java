package com.itinerarios.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoClase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private java.lang.String codigoClase;
	private String descripcion;

	public String getCodigoClase() {
		return codigoClase;
	}

	public void setCodigoClase(String codigoClase) {
		this.codigoClase = codigoClase;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
