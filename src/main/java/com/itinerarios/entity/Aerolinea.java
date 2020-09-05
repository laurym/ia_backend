package com.itinerarios.entity;

public class Aerolinea {
	// AEROLINEAS (GET)
//		{
//			"nombreAerolinea"
//			"codigoAerolinea"
//			"porcentajeDescuentoMenores" // (POR AHORA A NIVEL AEROLINEA)
//		}

	private Long id;
	private String nombreAerolinea;
	private String codigoAerolinea;
	private Long porcentajeDescuentoMenores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreAerolinea() {
		return nombreAerolinea;
	}

	public void setNombreAerolinea(String nombreAerolinea) {
		this.nombreAerolinea = nombreAerolinea;
	}

	public String getCodigoAerolinea() {
		return codigoAerolinea;
	}

	public void setCodigoAerolinea(String codigoAerolinea) {
		this.codigoAerolinea = codigoAerolinea;
	}

	public Long getPorcentajeDescuentoMenores() {
		return porcentajeDescuentoMenores;
	}

	public void setPorcentajeDescuentoMenores(Long porcentajeDescuentoMenores) {
		this.porcentajeDescuentoMenores = porcentajeDescuentoMenores;
	}

}
