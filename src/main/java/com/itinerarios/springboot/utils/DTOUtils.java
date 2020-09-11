package com.itinerarios.springboot.utils;

import org.modelmapper.ModelMapper;

import com.itinerarios.dto.AerolineaDTO;
import com.itinerarios.entity.Aerolinea;

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
}
