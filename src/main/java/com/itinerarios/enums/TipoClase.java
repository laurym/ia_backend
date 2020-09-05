package com.itinerarios.enums;

public enum TipoClase {
//	(Economica , Premium Economy ,Ejecutiva, Primera)
	ECONOMICA("ECONOMICA", "Económica"), PREMIUM("PREMIUM", "Premium Economy"), EJECUTIVA("EJECUTIVA", "Ejecutiva"),
	PRIMERA("PRIMERA", "Primera");

	private String valor;
	private String descripcion;

	TipoClase(String valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
