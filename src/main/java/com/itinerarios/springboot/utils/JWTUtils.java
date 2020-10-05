package com.itinerarios.springboot.utils;

import java.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTUtils {

	public static boolean verificarToken(String token) {
		token = token.substring(7);
		@SuppressWarnings("unused")
		Jws<Claims> jws;
		try {
			jws=Jwts.parserBuilder()
			.setSigningKey(Base64.getEncoder().encodeToString(ConstantsUtil.SECRETO.getBytes()))
			.build()
			.parseClaimsJws(token);
			return true;		 
		}
		catch (JwtException e) {
			return false;
		   
		}
	}
	
	public static String getId(String token) {
		token = token.substring(7);
		Jws<Claims> jws;
		try {
			jws=Jwts.parserBuilder()
			.setSigningKey(Base64.getEncoder().encodeToString(ConstantsUtil.SECRETO.getBytes()))
			.build()
			.parseClaimsJws(token);
			return jws.getBody().getSubject();	 
		}
		catch (JwtException e) {
			return null;
		   
		}
	}

}
