package com.itinerarios.springboot.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
					    				+  " and claseVuelo.asientosClaseDisponibles >= :asientosClase "
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
    	   	String formattedDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
						vuelosReturn.add(vuelo);
					}
				}
			}
//			vuelos.forEach(v -> System.out.println(v.getCodigo()));
	    			
			return vuelosReturn;
		}
		
}
