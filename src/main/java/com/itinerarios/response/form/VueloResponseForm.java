package com.itinerarios.response.form;

import java.util.List;

import com.itinerarios.dto.VueloDTO;

public class VueloResponseForm {

	private Double precioTotalPasajeros;

	private List<VueloDTO> listVuelos;
	private GeneralResponseForm mensaje;

	public Double getPrecioTotalPasajeros() {
		return precioTotalPasajeros;
	}

	public void setPrecioTotalPasajeros(Double precioTotalPasajeros) {
		this.precioTotalPasajeros = precioTotalPasajeros;
	}

	public List<VueloDTO> getListVuelos() {
		return listVuelos;
	}

	public void setListVuelos(List<VueloDTO> listVuelos) {
		this.listVuelos = listVuelos;
	}

	public GeneralResponseForm getMensaje() {
		return mensaje;
	}

	public void setMensaje(GeneralResponseForm mensaje) {
		this.mensaje = mensaje;
	}

}
