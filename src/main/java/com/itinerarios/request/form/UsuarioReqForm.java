package com.itinerarios.request.form;

import java.util.HashMap;
import java.util.List;

import com.itinerarios.dto.ClaseVueloDTO;
import com.itinerarios.dto.RecurrenciaVueloDTO;

public class UsuarioReqForm extends GeneralReqForm {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}

