package com.madhav.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.madhav.DTO.UserResource;
import com.madhav.Utils.UserUtils;
import com.madhav.configuration.JwtConstant;
import com.madhav.configuration.JwtProvider;
import com.madhav.enums.UserRole;
import com.madhav.exceptions.ApiException;
import com.madhav.model.User;
import com.madhav.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

    private final CustomUserImplementation customUserImplementation;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserUtils userUtils;

    AuthService(CustomUserImplementation customUserImplementation) {
        this.customUserImplementation = customUserImplementation;
    }

	public Optional<UserResource> register(UserResource userResource) throws ApiException {
		if (userResource.getRole() == UserRole.ROLE_ADMIN) {
			throw new ApiException("cannot register gor Admin Role");
		}
		User existingUser = userRepository.findByEmail(userResource.getEmail());
		if (existingUser != null) {
			throw new ApiException("Email already registered");
		}
		User user = userUtils.convertUserResourceIntoUser(userResource);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userResource.getEmail(), userResource.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtProvider.GenerateToken(authentication);

		userResource.setId(user.getId());
		userResource.setCreatedAt(user.getCreatedAt());
		userResource.setUpdatedAt(user.getUpdatedAt());
		userResource.setLastLogin(user.getLastLogin());
		userResource.setJwtToken(jwtToken);
		userResource.setMessage("user registered");
		userResource.setPassword(null);
		return Optional.of(userResource);
	}

	public Optional<UserResource> login(UserResource userResource) throws ApiException {
		Authentication authentication = Authenticate(userResource);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtProvider.GenerateToken(authentication);
		User user = userRepository.findByEmail(userResource.getEmail());
		userResource = userUtils.convertUserIntoUserResource(user);
		userResource.setJwtToken(jwtToken);
		userResource.setMessage("login Successful");
		return Optional.of(userResource);
	}

	private Authentication Authenticate(UserResource userResource) throws ApiException {
		UserDetails userDetails = customUserImplementation.loadUserByUsername(userResource.getEmail());
		if(userDetails == null) {
			throw new ApiException("email id does not exist");
		}
		if(!passwordEncoder.matches(userResource.getPassword(), userDetails.getPassword())) {
			throw new ApiException("Wrong credentials");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	}

	public Optional<UserResource> getUser(String value) throws ApiException {
	    User user = null;
	    // Check if input is UUID
	    try {
	        UUID id = UUID.fromString(value);
	        user = userRepository.findById(id).orElse(null);
	    } catch (IllegalArgumentException e) {
	        // Not a UUID → treat as email
	        user = userRepository.findByEmail(value);
	    }
	    if (user == null) {
	        throw new ApiException("Invalid id or email");
	    }
	    UserResource userResource = userUtils.convertUserIntoUserResource(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userResource.getEmail(), userResource.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwtToken = jwtProvider.GenerateToken(authentication);
	    userResource.setJwtToken(jwtToken);
	    userResource.setMessage("got the user");
	    return Optional.of(userResource);
	}


	public Collection<UserResource> getAllUser() throws ApiException {
		List<User> users = userRepository.findAll();
		if(users == null || users.size() == 0) {
			throw new ApiException("No user exists");
		}
		Collection<UserResource> usersResponse = new ArrayList<>();
		for(User user : users) {
			UserResource userResponse = userUtils.convertUserIntoUserResource(user);
			Authentication authentication = new UsernamePasswordAuthenticationToken(userResponse.getEmail(), userResponse.getPassword());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		    String jwtToken = jwtProvider.GenerateToken(authentication);
		    userResponse.setJwtToken(jwtToken);
			usersResponse.add(userResponse);
		}
		return usersResponse;
	}

	public Optional<UserResource> getCurrentUser() throws ApiException {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new ApiException("no current user");
		}
		UserResource userResponse = userUtils.convertUserIntoUserResource(user);
		Authentication authentication = Authenticate(userResponse);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwtToken = jwtProvider.GenerateToken(authentication);
	    userResponse.setJwtToken(jwtToken);
	    userResponse.setMessage("got the current user");
		return Optional.of(userResponse);
	}

	public Optional<UserResource> getUserByJwt(UserResource userResource) throws ApiException {
		String token = userResource.getJwtToken();
		SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
		Claims cliams = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
		String email = String.valueOf(cliams.get("email"));
		if(email == null) {
			throw new ApiException("email does not exist");
		}
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new ApiException("user does not exist");
		}
		UserResource userResponse = userUtils.convertUserIntoUserResource(user);
		Authentication authentication = Authenticate(userResponse);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwtToken = jwtProvider.GenerateToken(authentication);
	    userResponse.setJwtToken(jwtToken);
	    userResponse.setMessage("got the user");
		return Optional.of(userResponse);
	}

}
