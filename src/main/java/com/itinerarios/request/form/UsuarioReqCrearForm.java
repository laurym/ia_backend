package com.itinerarios.request.form;

import java.util.HashMap;
import java.util.List;

import com.itinerarios.dto.ClaseVueloDTO;
import com.itinerarios.dto.RecurrenciaVueloDTO;

public class UsuarioReqCrearForm extends GeneralReqForm {

	private String nombre;
	private String apellido;
	private String password;
	private String usuario;
	private String mail;
	private String fecha_nacimiento;
	private String telefono;
	private HashMap<String,String> propiedades;
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public HashMap<String, String> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(HashMap<String, String> propiedades) {
		this.propiedades = propiedades;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	

}

