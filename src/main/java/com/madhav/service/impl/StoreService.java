package com.madhav.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.madhav.enums.UserRole;
import com.madhav.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.madhav.DTO.StoreResource;
import com.madhav.DTO.UserResource;
import com.madhav.Utils.StoreUtils;
import com.madhav.configuration.JwtProvider;
import com.madhav.enums.StoreStatus;
import com.madhav.model.Store;
import com.madhav.repository.StoreRepository;

public class StoreService {

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	StoreUtils storeUtils;
	
	@Autowired
	StoreRepository storeRepository;

	@Autowired
	AuthService userService;

	public Optional<StoreResource> createStore(StoreResource storeResource, UserResource userResource) {
		// TODO Auto-generated method stub
		Store store = storeUtils.convertStoreResourceIntoStore(storeResource, userResource);
		
		store.setCreatedAt(LocalDateTime.now());
		store.setUpdatedAt(LocalDateTime.now());
		store.setLastLogin(LocalDateTime.now());
		storeRepository.save(store);
		storeResource.setId(store.getId());
		storeResource.setCreatedAt(store.getCreatedAt());
		storeResource.setStatus(StoreStatus.PENDING);
		storeResource.setUpdatedAt(store.getUpdatedAt());
		storeResource.setLastLogin(store.getLastLogin());

		Authentication authentication = new UsernamePasswordAuthenticationToken(userResource.getEmail(), userResource.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtProvider.GenerateToken(authentication);
		storeResource.setJwtToken(jwtToken);
		storeResource.setMessage("Store Registerd");
		return Optional.of(storeResource);
	}

	public Optional<StoreResource> getStoreById(UUID id) throws ApiException {
		Optional<UserResource> existingUser = userService.getCurrentUser();
		if (existingUser.isEmpty()) {
			throw new RuntimeException("User is not logged in");
		}
		UserResource userResource = existingUser.get();
		if(id == null) {
			throw new ApiException("store id is null");
		}
		Optional<Store> optionalStore = storeRepository.findById(id);
		if(optionalStore.isEmpty()) {
			throw new ApiException("store not registered with id {}", id);
		}
		Store store =  optionalStore.get();

		StoreResource storeResource = storeUtils.convertStoreIntoStoreResource(store);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userResource.getEmail(), userResource.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtProvider.GenerateToken(authentication);
		storeResource.setJwtToken(jwtToken);
		storeResource.setMessage("Store Registerd");
		return Optional.of(storeResource);
	}

	public Optional<StoreResource> updateStore(UUID id, StoreResource storeResource) throws ApiException {
		Optional<UserResource> existingUser = userService.getCurrentUser();
		if (existingUser.isEmpty()) {
			throw new RuntimeException("User is not logged in");
		}
		UserResource userResource = existingUser.get();
		if(userResource.getRole() != UserRole.ROLE_ADMIN) {
			throw new RuntimeException("User is not admin");
		}
		Store store = storeUtils.convertStoreResourceIntoStore(storeResource, userResource);
		storeRepository.save(store);
		storeResource.setId(store.getId());
		return Optional.of(storeResource);
	}

	public List<StoreResource> getAllStores() throws ApiException {
		Optional<UserResource> existingUser = userService.getCurrentUser();
		if(existingUser.isEmpty()) {
			throw new RuntimeException("User is not logged in");
		}
		UserResource userResource = existingUser.get();
		if(userResource.getRole() != UserRole.ROLE_ADMIN) {
			throw new RuntimeException("User is not admin");
		}
		List<Store> stores = storeRepository.findAll();
		List<StoreResource> storeResources = new ArrayList<>();
		stores.parallelStream().forEach(store -> {
			StoreResource storeResource = storeUtils.convertStoreIntoStoreResource(store);
			storeResources.add(storeResource);
		});
		return storeResources;
	}

	public void deleteStore(UUID id) throws ApiException {
		Optional<UserResource> existingUser = userService.getCurrentUser();
		if (existingUser.isEmpty()) {
			throw new RuntimeException("User is not logged in");
		}
		UserResource userResource = existingUser.get();
		if(userResource.getRole() != UserRole.ROLE_ADMIN) {
			throw new RuntimeException("User is not admin");
		}
		Store store = storeRepository.getReferenceById(id);
		storeRepository.delete(store);
	}

	public Optional<StoreResource> getStoreByEmployee() throws ApiException {
		Optional<UserResource> existingUser = userService.getCurrentUser();
		if (existingUser.isEmpty()) {
			throw new RuntimeException("User is not logged in");
		}
		UserResource userResource = existingUser.get();
		Store store = storeRepository.findByStoreAdminId(userResource.getId());
		StoreResource storeResource = storeUtils.convertStoreIntoStoreResource(store);
		return Optional.of(storeResource);
	}

	public Optional<StoreResource> getStoreByAdmin() throws ApiException {
		Optional<UserResource> existingUser = userService.getCurrentUser();
		if (existingUser.isEmpty()) {
			throw new RuntimeException("User is not logged in");
		}
		UserResource userResource = existingUser.get();
		Store store = storeRepository.findByStoreAdminId(userResource.getId());
		StoreResource storeResource = storeUtils.convertStoreIntoStoreResource(store);
		return Optional.of(storeResource);
	}
}
