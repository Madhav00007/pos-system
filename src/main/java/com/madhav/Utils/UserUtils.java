package com.madhav.Utils;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.madhav.DTO.UserResource;
import com.madhav.configuration.SecurityConfig;
import com.madhav.model.User;

@Component
public class UserUtils {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User convertUserResourceIntoUser(UserResource userResponse) {
		User user = new User();
		user.setEmail(userResponse.getEmail());
		user.setFullName(userResponse.getFullName());
		user.setPassword(passwordEncoder.encode(userResponse.getPassword()));
		user.setPhone(userResponse.getPhone());
		user.setRole(userResponse.getRole());
		return user;
		
	}
	
	public UserResource convertUserIntoUserResource(User user) {
		UserResource userResource = new UserResource();
		userResource.setEmail(user.getEmail());
		userResource.setFullName(user.getFullName());
		userResource.setPassword(null);
		userResource.setPhone(user.getPhone());
		userResource.setRole(user.getRole());
		userResource.setCreatedAt(user.getCreatedAt());
		userResource.setUpdatedAt(user.getUpdatedAt());
		userResource.setLastLogin(LocalDateTime.now());
		userResource.setId(user.getId());
		return userResource;
	}

}
