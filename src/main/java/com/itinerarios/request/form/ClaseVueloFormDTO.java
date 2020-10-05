package com.itinerarios.request.form;

public class ClaseVueloFormDTO {

	private String codigoClase;
	private Long asientosClaseDisponibles;
	private Double precio;
	

	public String getCodigoClase() {
		return codigoClase;
	}

	public void setCodigoClase(String clase) {
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
