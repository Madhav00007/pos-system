package com.madhav.configuration;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
	static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
	
	
	public String GenerateToken(Authentication authentication) {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		String roles = PopulateAuthority(authorities);
		
		return Jwts.builder()
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime()+86400000))
				.claim("email", authentication.getName())
				.claim("authorities", roles)
				.signWith(key)
				.compact();
		
	}
	
	private String PopulateAuthority(Collection<? extends GrantedAuthority> authorities) {
		
		Set<String> auths = new HashSet<>();
		for(GrantedAuthority authority : authorities) {
			auths.add(authority.getAuthority());
		}
		return String.join(",", auths);
		
	}
	
	public String getEmailFromJwtToken(String jwt) {
		
		String token = jwt.substring(7);
		SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
		Claims cliams = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
		return String.valueOf(cliams.get("email"));
		
	}

}
