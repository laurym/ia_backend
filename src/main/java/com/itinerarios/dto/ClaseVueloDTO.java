package com.itinerarios.dto;

public class ClaseVueloDTO {
//	 {
//			"tipoClase" (Economica , Premium Economy ,Ejecutiva, Primera)
//			"asientosDisponibles"
//			"precioClase"
//		  }

	private String tipoClase;
	private Long asientosDisponibles;
	private Double precioClase;
	private String codigoVuelo;
	
	public String getTipoClase() {
		return tipoClase;
	}

	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
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

	public String getCodigoVuelo() {
		return codigoVuelo;
	}

	public void setCodigoVuelo(String codigoVuelo) {
		this.codigoVuelo = codigoVuelo;
	}

}
