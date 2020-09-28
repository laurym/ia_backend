package com.itinerarios.response.form;

import java.util.List;

import com.itinerarios.dto.VueloDTO;

public class VueloResponseForm {

	private List<VueloDTO> listVuelos;
	private List<String> mensaje;
	
	private String codigo;
	private String mensajeResultado;

	public List<VueloDTO> getListVuelos() {
		return listVuelos;
	}

	public void setListVuelos(List<VueloDTO> listVuelos) {
		this.listVuelos = listVuelos;
	}

	public List<String> getMensaje() {
		return mensaje;
	}

	public void setMensaje(List<String> mensaje) {
		this.mensaje = mensaje;
	}

	

}
