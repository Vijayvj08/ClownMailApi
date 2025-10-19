package com.vijay.clownmail.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String SECRET_KEY;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	///generate Token
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	//Extract all claims
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	//Extract email from JWT token
	public String extractEmail(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	//validate token
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(getSignKey())
			.build()
			.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			System.out.println("Invalid JWT: " + e.getMessage());
		}
		return false;
	}
	
	
	
}
