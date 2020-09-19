package com.itinerarios.springboot.utils;

import org.modelmapper.ModelMapper;

import com.itinerarios.dto.AerolineaDTO;
import com.itinerarios.dto.AeropuertoDTO;
import com.itinerarios.dto.TipoClaseDTO;
import com.itinerarios.dto.VueloDTO;
import com.itinerarios.entity.Aerolinea;
import com.itinerarios.entity.Aeropuerto;
import com.itinerarios.entity.TipoClase;
import com.itinerarios.entity.Vuelo;

public class DTOUtils {

	
	public static AerolineaDTO convertToDto(Aerolinea obj) {
		ModelMapper modelMapper = new ModelMapper();
		AerolineaDTO dto = modelMapper.map(obj, AerolineaDTO.class);
	    return dto;
	}
	
	public static Aerolinea convertToEntity(AerolineaDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Aerolinea obj = modelMapper.map(dto, Aerolinea.class);
	    return obj;
	}
	
	public static AeropuertoDTO convertToDto(Aeropuerto obj) {
		ModelMapper modelMapper = new ModelMapper();
		AeropuertoDTO dto = modelMapper.map(obj, AeropuertoDTO.class);
	    return dto;
	}
	
	public static Aeropuerto convertToEntity(AeropuertoDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Aeropuerto obj = modelMapper.map(dto, Aeropuerto.class);
	    return obj;
	}
	
	public static TipoClaseDTO convertToDto(TipoClase obj) {
		ModelMapper modelMapper = new ModelMapper();
		TipoClaseDTO dto = modelMapper.map(obj, TipoClaseDTO.class);
	    return dto;
	}
	
	public static TipoClase convertToEntity(TipoClaseDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		TipoClase obj = modelMapper.map(dto, TipoClase.class);
	    return obj;
	}
	
	public static VueloDTO convertToDto(Vuelo obj) {
		ModelMapper modelMapper = new ModelMapper();
		VueloDTO dto = modelMapper.map(obj, VueloDTO.class);
	    return dto;
	}
	
	public static Vuelo convertToEntity(VueloDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Vuelo obj = modelMapper.map(dto, Vuelo.class);
	    return obj;
	}
	
}
