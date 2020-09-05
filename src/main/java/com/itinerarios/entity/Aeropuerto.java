package com.itinerarios.entity;

public class Aeropuerto {

	// AEROPUERTOS (GET)
//		{
//			"ciudad"
//			"pais"
//			"acronimo"
//		}

	private Long id;
	private String ciudad;
	private String pais;
	private String acronimo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

}
