package com.vijay.clownmail.Utils;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final Key SECRETE_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 1000*60*60*10;
	
	public static String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRETE_KEY)
				.compact();
	}
	
	public static String validateToken(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(SECRETE_KEY)
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		} catch (Exception e) {
			return null;
		}
	}
}
