package com.itinerarios.request.form;

public class VueloReqConfirmarForm extends GeneralReqForm {

	private String codigoVuelo;
	private String codigoClase;
	private Long cantidadPasajeros;

	public String getCodigoVuelo() {
		return codigoVuelo;
	}

	public void setCodigoVuelo(String codigoVuelo) {
		this.codigoVuelo = codigoVuelo;
	}

	public String getCodigoClase() {
		return codigoClase;
	}

	public void setCodigoClase(String codigoClase) {
		this.codigoClase = codigoClase;
	}

	public Long getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public void setCantidadPasajeros(Long cantidadPasajeros) {
		this.cantidadPasajeros = cantidadPasajeros;
	}

}
