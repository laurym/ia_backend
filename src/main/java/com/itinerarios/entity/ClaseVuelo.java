package com.itinerarios.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clasePorVuelo")
public class ClaseVuelo {
//	 {
//			"tipoClase" (Economica , Premium Economy ,Ejecutiva, Primera)
//			"asientosDisponibles"
//			"precioClase"
//		  }

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn( name="codigoVuelo", nullable=true)
	private Vuelo codigoVuelo;
	
	@OneToOne
	@JoinColumn(name = "codigoClase", referencedColumnName = "codigoClase", nullable = false)
	private TipoClase codigoClase;
	
	private Long asientosClaseDisponibles;
	
	private Double precio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vuelo getCodgioVuelo() {
		return codigoVuelo;
	}

	public void setCodigoVuelo(Vuelo aeropuerto) {
		this.codigoVuelo = aeropuerto;
	}

	public TipoClase getCodigoClase() {
		return codigoClase;
	}

	public void setCodigoClase(TipoClase clase) {
		this.codigoClase = clase;
	}

	public Long getAsientosClaseDisponibles() {
		return asientosClaseDisponibles;
	}

	public void setAsientosClaseDisponibles(Long asientosDisponibles) {
		this.asientosClaseDisponibles = asientosDisponibles;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precioClase) {
		this.precio = precioClase;
	}

}
