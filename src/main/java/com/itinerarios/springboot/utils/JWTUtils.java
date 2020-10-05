package com.itinerarios.springboot.utils;

import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTUtils {

	public static boolean verificarToken(String token) {
		token = token.substring(7);
		Jws<Claims> jws;
		try {
			jws=Jwts.parserBuilder()
			.setSigningKey(Base64.getEncoder().encodeToString(ConstantsUtil.SECRETO.getBytes()))
			.build()
			.parseClaimsJws(token);
			return true;		 
		}
		catch (JwtException e) {
			System.out.println(e.toString()+"\n"+e.getMessage());
		    return false;
		   
		}
	}

}
