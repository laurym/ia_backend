package com.itinerarios.springboot.utils;

public class ConstantsUtil {
	
	public static String API_KEY ="x-api-key";
	public static String TENANT="tenant";
	public static String ALGORITHM="HS512";
	
	public static String FORMAT_FECHA= "dd/MM/yyyy";
	public static String FORMAT_FECHA_CON_HORA= "dd/MM/yyyy HH:mm:ss";
	public static String CODIGO_AEROLINEA="CODIGO_AEROLINEA";
	public static String ENDPOINT_LOGOS="http://pics.avs.io/200/200/CODIGO_AEROLINEA.png"; 
	public static String CODIGO_CLASE_DEFAULT = "YC";
	public static Long CANTIDAD_PASAJEROS_DEFAULT=1L;
	
	
//	x-api-key = tenant 
//	para desencriptar un token , se usa el secreto "public_token"
//	que es la clave publica que nos pasaron tambien fija
}
