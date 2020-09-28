package com.itinerarios.response.form;

public class GeneralResponseForm {

	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public GeneralResponseForm() {}
	
	public GeneralResponseForm(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	
}
