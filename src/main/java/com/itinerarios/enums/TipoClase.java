package com.itinerarios.enums;
public enum TipoClase {
	ECONOMICA("YC", "Económica"), PREMIUM("PE", "Premium Economy"), EJECUTIVA("C", "Ejecutiva"),
//	(Economica , Premium Economy ,Ejecutiva, Primera)
	PRIMERA("F", "Primera");
	
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

