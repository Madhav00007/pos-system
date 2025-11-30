package com.madhav.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.madhav.DTO.UserResource;
import com.madhav.exceptions.ApiException;
import com.madhav.service.impl.AuthService;

@RestController
public class UserController {

	@Autowired
	AuthService userService;

	@PostMapping("/auth/signup")
	public Optional<UserResource> registerUser(@RequestBody UserResource userResource) throws ApiException {
		return userService.register(userResource);
	}

	@PostMapping("/auth/login")
	public Optional<UserResource> loginUser(@RequestBody UserResource userResource) throws ApiException {
		return userService.login(userResource);
	}
	
	@GetMapping("/users/{value}")
	public Optional<UserResource> getUser(@PathVariable String value) throws ApiException {
		return userService.getUser(value);
	}
	
	@GetMapping("/users")
	public Collection<UserResource> getAllUser() throws ApiException {
		return userService.getAllUser();
	}
	
	@GetMapping("/currentUser")
	public Optional<UserResource> getCurrentUser() throws ApiException {
		return userService.getCurrentUser();
	}
	
	@PostMapping("/user")
	public Optional<UserResource> getUserByJwt(@RequestBody UserResource userResource) throws ApiException {
		return userService.getUserByJwt(userResource);
	}

}
