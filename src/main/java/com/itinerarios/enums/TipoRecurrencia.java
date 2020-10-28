package com.itinerarios.enums;

public enum TipoRecurrencia {
	UNICO("UNA VEZ"), SEMANAL("SEMANAL"), MENSUAL("MENSUAL");

	private String valor;

	private TipoRecurrencia(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
