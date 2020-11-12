package com.itinerarios.springboot.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.itinerarios.entity.ClaseVuelo;
import com.itinerarios.entity.Vuelo;

public class VueloRepositoryCustomImpl implements VueloRepositoryCustom{

	    @PersistenceContext
	    private EntityManager entityManager;
	 
	    @Override
//		@Query("select v from Vuelo v where v.aeropuerto.id = :aeropuerto and 
//	    v.aeropuertoDestino.id = :aeropuertoDestino and v.fechaPartida= :date
//	    and v.clases.codigoClase = :tipoClase order by v.horaPartida asc")

	    public List<Vuelo> buscarPorAeropuertoAeropuertoDestinoFechaClase(Long aeropuerto, Long aeropuertoDestino, Long asientosDisponibles, String date, String tipoClase){
	    	String consultaPorVuelos = "from Vuelo as vuelo JOIN ClaseVuelo  as claseVuelo"
					    				+ " on vuelo.codigo =  claseVuelo.codigoVuelo.codigo"
					    				+  " WHERE vuelo.aeropuerto.id = :aeropuerto and"
					    				+  " vuelo.aeropuertoDestino.id = :aeropuertoDestino"
					    				+  " and vuelo.fechaPartida = :date "
					    				+  " and claseVuelo.codigoClase.codigoClase = :tipoClase "
					    				+  " and vuelo.disponible = :disponible "
					    				+  " and claseVuelo.asientosClaseDisponibles  >= :asientosClase "
					    				+  " order by vuelo.horaPartida asc";
	    	Query query = entityManager.createQuery(consultaPorVuelos);
	    	
	    	query.setParameter("aeropuerto", aeropuerto);
	    	query.setParameter("aeropuertoDestino", aeropuertoDestino);
	    	query.setParameter("date", date);
	    	query.setParameter("tipoClase", tipoClase);
	    	query.setParameter("asientosClase", asientosDisponibles);
	    	query.setParameter("disponible", true);
	    	
	    	List<Object[]> vuelosBase  = query.getResultList();
	    	List<Vuelo> vuelosReturn = new ArrayList<Vuelo>();
			for (Object[] value : vuelosBase) {
				Vuelo vuelo = null;
				for (Object object : value) {
					if (object.getClass().equals(Vuelo.class)) {
						vuelo = (Vuelo) object;
					}
					if(object.getClass().equals(ClaseVuelo.class)) {
						Set<ClaseVuelo> clases = new HashSet<ClaseVuelo>();
						clases.add((ClaseVuelo) object);
						vuelo.setClases(clases);
						vuelosReturn.add(vuelo);
					}
				}
			}
//	    	vuelosReturn.forEach(v -> System.out.println(v.getCodigo()));

	    	return vuelosReturn;
	    }

	    @Override
	    public List<Vuelo> buscarPorAeropuertoAeropuertoDestinoClase(Long aeropuerto, Long aeropuertoDestino, Long asientosDisponibles, String tipoClase){
			
			String consultaPorVuelos = "from Vuelo vuelo JOIN ClaseVuelo claseVuelo on vuelo.codigo =  claseVuelo.codigoVuelo.codigo"
									+  " WHERE vuelo.aeropuerto.id = :aeropuerto "
									+  " and vuelo.aeropuertoDestino.id = :aeropuertoDestino"
									+  " and claseVuelo.codigoClase.codigoClase = :tipoClase "
				    				+  " and claseVuelo.asientosClaseDisponibles >= :asientosClase "
									+  " and vuelo.fechaPartida >=  :fecha "
				    				+  " and vuelo.disponible = :disponible "
									+ " ORDER by vuelo.fechaPartida asc, vuelo.horaPartida asc";
			
			Query query = entityManager.createQuery(consultaPorVuelos);
			query.setParameter("aeropuerto", aeropuerto);
	    	query.setParameter("aeropuertoDestino", aeropuertoDestino);
	    	query.setParameter("tipoClase", tipoClase);
	    	query.setParameter("disponible", true);
	    	LocalDate today = LocalDate.now();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	   	String formattedDate = today.format(formatter);
	    	query.setParameter("fecha", "\'"+formattedDate+"\'");
	    	
	    	query.setParameter("asientosClase", asientosDisponibles);
	    	
	    	List<Object[]> vuelosBase  = query.getResultList();
	    	List<Vuelo> vuelosReturn = new ArrayList<Vuelo>();
			for (Object[] value : vuelosBase) {
				Vuelo vuelo = null;
				for (Object object : value) {
					if (object.getClass().equals(Vuelo.class)) {
						vuelo = (Vuelo) object;
					}
					if(object.getClass().equals(ClaseVuelo.class)) {
						Set<ClaseVuelo> clases = new HashSet<ClaseVuelo>();
						clases.add((ClaseVuelo) object);
						vuelo.setClases(clases);
						if ((LocalDate.parse(vuelo.getFechaPartida(), formatter)).compareTo(LocalDate.parse(formattedDate, formatter)) >0)
							vuelosReturn.add(vuelo);
					}
				}
			}
//			vuelos.forEach(v -> System.out.println(v.getCodigo()));
	    			
			return vuelosReturn;
		}
	    
	    public List<Vuelo> buscarPorAerolineaAeropuertoAeropuertoDestinoFecha(Long aeropuerto, Long aeropuertoDestino, Long aerolineaId, String date, String dateFin){
	    	
	    	String consultaPorVuelos = "from Vuelo as vuelo JOIN ClaseVuelo  as claseVuelo"
	    			+ " on vuelo.codigo =  claseVuelo.codigoVuelo.codigo"
					+  " WHERE "
					+  " vuelo.aerolinea.id = :aerolineaId"
					
					+  ((aeropuerto!=null)? " and vuelo.aeropuerto.id = :aeropuerto " :"")
					+  ((aeropuertoDestino!=null)? " and vuelo.aeropuertoDestino.id = :aeropuertoDestino" : "")
					+	" and vuelo.fechaPartida >=  :fecha "
//					+   ((dateFin!=null)? " and vuelo.fechaPartida <= :fechaFin " : "")
    				+  " and vuelo.disponible = :disponible "
//    				+  " and vuelo.asientosVendidos = 0 "
					+ " ORDER by vuelo.fechaPartida asc, vuelo.horaPartida asc";

			Query query = entityManager.createQuery(consultaPorVuelos);
			if (aeropuerto!=null)
			query.setParameter("aeropuerto", aeropuerto);
			if (aeropuertoDestino!=null)
			query.setParameter("aeropuertoDestino", aeropuertoDestino);	
			query.setParameter("aerolineaId", aerolineaId);
			query.setParameter("disponible", true);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = "";
			if(date== null || date.isEmpty()) {
				LocalDate today = LocalDate.now();

				formattedDate = today.format(formatter);
				query.setParameter("fecha", "\'"+formattedDate+"\'");
			} else {
				formattedDate = date;
				query.setParameter("fecha", "\'"+date+"\'");
			}
			
//			if (dateFin != null)
//				query.setParameter("fechaFin", "\'"+dateFin+"\'");
//			
			List<Object[]> vuelosBase  = query.getResultList();
	    	List<Vuelo> vuelosReturn = new ArrayList<Vuelo>();
	    	
			for (Object[] value : vuelosBase) {
				Boolean encontrado = Boolean.FALSE;
				Vuelo vuelo = null;
				for (Object object : value) {
					if (object.getClass().equals(Vuelo.class)) {
						vuelo = (Vuelo) object;
					}
					if(object.getClass().equals(ClaseVuelo.class)) {
						Set<ClaseVuelo> clases = new HashSet<ClaseVuelo>();
						clases.add((ClaseVuelo) object);
						vuelo.setClases(clases);
						
						if (dateFin != null) {
							if (((LocalDate.parse(vuelo.getFechaPartida(), formatter)).compareTo(LocalDate.parse(formattedDate, formatter)) >= 0)
									&& ((LocalDate.parse(dateFin, formatter)).compareTo(LocalDate.parse(vuelo.getFechaPartida(), formatter)) > 0)) {
									for (Vuelo vueloIdx :vuelosReturn) {
										if(vueloIdx.getCodigo().compareTo(vuelo.getCodigo()) ==0) {
											encontrado = Boolean.TRUE;
											break;
										}
									}
									if (!encontrado)
										vuelosReturn.add(vuelo);
							}
						} else {
							if ((LocalDate.parse(vuelo.getFechaPartida(), formatter)).compareTo(LocalDate.parse(formattedDate, formatter)) >= 0) {
								for (Vuelo vueloIdx :vuelosReturn) {
									if(vueloIdx.getCodigo().compareTo(vuelo.getCodigo()) ==0) {
										encontrado = Boolean.TRUE;
										break;
									}
								}
								if (!encontrado)
									vuelosReturn.add(vuelo);
						}
					}
				}
			}
			
			}
	    	return vuelosReturn;
	    }
		
}
