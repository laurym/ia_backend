package com.itinerarios.dto;

import com.itinerarios.enums.TipoRecurrencia;

public class RecurrenciaVueloDTO {

	private TipoRecurrencia tipoRecurrencia;
	private Long cantidadRecurrencia;

	public TipoRecurrencia getTipoRecurrencia() {
		return tipoRecurrencia;
	}

	public void setTipoRecurrencia(TipoRecurrencia tipoRecurrencia) {
		this.tipoRecurrencia = tipoRecurrencia;
	}

	public Long getCantidadRecurrencia() {
		return cantidadRecurrencia;
	}

	public void setCantidadRecurrencia(Long cantidadRecurrencia) {
		this.cantidadRecurrencia = cantidadRecurrencia;
	}

}
