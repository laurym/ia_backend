package com.itinerarios.springboot.utils;

public class ConstantsUtil {
	
	public static String API_KEY ="667CC8E484F91BCC89";
	public static String SECRETO = "50E25EC9B0517772A7EA5A4078A69E38AE0B48BDFFE1857D63933885829723C8";
	public static String TENANT="tenant";
	public static String ALGORITHM="HS512";
	
	public static String FORMAT_FECHA= "dd/MM/yyyy";
	public static String FORMAT_FECHA_CON_HORA= "dd/MM/yyyy HH:mm:ss";
	public static String CODIGO_AEROLINEA="CODIGO_AEROLINEA";
	public static String ENDPOINT_LOGOS="http://pics.avs.io/200/200/CODIGO_AEROLINEA.png"; 
	public static String CODIGO_CLASE_DEFAULT = "YC";
	public static Long CANTIDAD_PASAJEROS_DEFAULT=1L;
	public static Long MULTIPLIER_MINUTE = 60000L;
	
//	x-api-key = tenant 
//	para desencriptar un token , se usa el secreto "public_token"
//	que es la clave publica que nos pasaron tambien fija
}
