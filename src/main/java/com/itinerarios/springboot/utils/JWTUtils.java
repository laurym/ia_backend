package com.itinerarios.springboot.utils;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTUtils {

	public static boolean verificarToken(String token) {
		Claims claims;
		token = token.substring(7);
		try {

			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(ConstantsUtil.SECRETO))
					.parseClaimsJws(token).getBody();
			return true;
		} catch (io.jsonwebtoken.SignatureException e) {
			return false;
		}
	}

}
