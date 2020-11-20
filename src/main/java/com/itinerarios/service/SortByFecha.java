package com.itinerarios.service;

import java.util.Comparator;

import com.itinerarios.dto.VueloDTO;

public class SortByFecha implements Comparator<VueloDTO> {

	@Override
	public int compare(VueloDTO vuelo, VueloDTO otherVuelo) {
		// TODO Auto-generated method stub
		return vuelo.getFechaPartidaDTO().compareTo(otherVuelo.getFechaPartidaDTO());
	}

}
