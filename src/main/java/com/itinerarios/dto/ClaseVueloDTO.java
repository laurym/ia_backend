package com.itinerarios.dto;

public class ClaseVueloDTO {
//	 {
//			"tipoClase" (Economica , Premium Economy ,Ejecutiva, Primera)
//			"asientosDisponibles"
//			"precioClase"
//		  }

	private Long id;
	private TipoClaseDTO codigoClase;
	private Long asientosClaseDisponibles;
	private Double precio;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoClaseDTO getClase() {
		return codigoClase;
	}

	public void setClase(TipoClaseDTO clase) {
		this.codigoClase = clase;
	}

	public Long getAsientosClaseDisponibles() {
		return asientosClaseDisponibles;
	}

	public void setAsientosClaseDisponibles(Long asientosDisponibles) {
		this.asientosClaseDisponibles = asientosDisponibles;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precioClase) {
		this.precio = precioClase;
	}

}
