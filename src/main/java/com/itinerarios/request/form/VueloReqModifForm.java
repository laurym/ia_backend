package com.itinerarios.request.form;

public class VueloReqModifForm extends GeneralReqForm {

	private String fechaInicio;
	private String horaInicio;
	private String duracion;
	
	private Boolean isDisponible;
	private String aerolineaCodigo;
	private String codigoVuelo;


	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getDuracion() {
		return duracion;
	}

	/**
	 * 
	 * @param duracion debe estar expresado en minutos de duracion
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Boolean getIsDisponible() {
		return isDisponible;
	}

	public void setIsDisponible(Boolean isDisponible) {
		this.isDisponible = isDisponible;
	}

	public String getAerolineaCodigo() {
		return aerolineaCodigo;
	}

	public void setAerolineaCodigo(String aerolineaCodigo) {
		this.aerolineaCodigo = aerolineaCodigo;
	}

	public String getCodigoVuelo() {
		return codigoVuelo;
	}

	public void setCodigoVuelo(String codigoVuelo) {
		this.codigoVuelo = codigoVuelo;
	}


}
