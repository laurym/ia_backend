package com.itinerarios.response.form;

import java.util.List;

import com.itinerarios.dto.VueloDTO;

public class VueloResponseForm {

	private List<VueloDTO> listVuelos;
//	private GeneralResponseForm mensaje;
	private Integer cantidadTotalPaginas;
	private Long elementosTotal;
	private Integer numeroDeElementos;
	private Long numeroPagina;

	public List<VueloDTO> getListVuelos() {
		return listVuelos;
	}

	public void setListVuelos(List<VueloDTO> listVuelos) {
		this.listVuelos = listVuelos;
	}

//	public GeneralResponseForm getMensaje() {
//		return mensaje;
//	}
//
//	public void setMensaje(GeneralResponseForm mensaje) {
//		this.mensaje = mensaje;
//	}

	public Integer getCantidadTotalPaginas() {
		return cantidadTotalPaginas;
	}

	public void setCantidadTotalPaginas(Integer cantidadTotalPaginas) {
		this.cantidadTotalPaginas = cantidadTotalPaginas;
	}

	public Long getElementosTotal() {
		return elementosTotal;
	}

	public void setElementosTotal(Long elementosTotal) {
		this.elementosTotal = elementosTotal;
	}

	public Integer getNumeroDeElementosPagina() {
		return numeroDeElementos;
	}

	public void setNumeroDeElementosPagina(Integer numeroDeElementos) {
		this.numeroDeElementos = numeroDeElementos;
	}

	public Long getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Long numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

}
