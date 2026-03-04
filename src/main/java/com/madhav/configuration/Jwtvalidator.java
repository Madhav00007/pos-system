package com.madhav.configuration;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Jwtvalidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		if(jwt != null) {
			String token = jwt.substring(7);
			
			try {
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
				Claims cliams = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
				
				String email = String.valueOf(cliams.get("email"));
				String authorities = String.valueOf(cliams.get("authorities"));
				
				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication auth = new UsernamePasswordAuthenticationToken(email, null, auths);
				SecurityContextHolder.getContext().setAuthentication(auth);
			} catch(Exception e) {
				throw new BadCredentialsException("Invalid JWT...");
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
