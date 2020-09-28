package com.itinerarios.response.form;

import java.util.List;

import com.itinerarios.dto.VueloDTO;

public class VueloResponseForm {

	private List<VueloDTO> listVuelos;
	private GeneralResponseForm mensaje;
	
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
