package com.itinerarios.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clasePorVuelo")
public class ClaseVuelo {
//	 {
//			"tipoClase" (Economica , Premium Economy ,Ejecutiva, Primera)
//			"asientosDisponibles"
//			"precioClase"
//		  }

	@EmbeddedId
	private ClaseVueloId id;
	private Long asientosDisponibles;
	private Double precioClase;

	public ClaseVueloId getId() {
		return id;
	}

	public void setId(ClaseVueloId id) {
		this.id = id;
	}

	public Long getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(Long asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}

	public Double getPrecioClase() {
		return precioClase;
	}

	public void setPrecioClase(Double precioClase) {
		this.precioClase = precioClase;
	}

}
