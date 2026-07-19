package com.deepanshu.boot.util;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	private static String SECRET_KEY;

	// to generate the key

	public JWTUtil() {
		SecureRandom randon = new SecureRandom();
		byte[] key = new byte[32];
		randon.nextBytes(key);
		SECRET_KEY = Base64.getEncoder().encodeToString(key);
	}

	public String generateToken(String userName, List<String> roles) {
		return Jwts.builder().setSubject(userName).claim("roles", roles)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 5))
				.signWith(getSecurityKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSecurityKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}

	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	public Date extractExpiry(String token) {
		return extractClaims(token, Claims::getExpiration);
	}

	public Boolean isTokenValid(String token) {

		return extractExpiry(token).before(new Date());
	}

	public List<String> extractroles(String token) {

		return extractClaims(token, claims -> claims.get("roles", List.class));
	}

	public Boolean validToken(String token, String userName) {
		
		return extractUserName(token).equals(userName) && !isTokenValid(token);
	}

	// extract the information related to user from the token
	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		final Claims claim = Jwts.parserBuilder().setSigningKey(getSecurityKey()).build().parseClaimsJws(token)
				.getBody();

		return claimsResolver.apply(claim);
	}

}
