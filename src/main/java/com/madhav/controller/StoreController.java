package com.madhav.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.madhav.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.madhav.DTO.StoreResource;
import com.madhav.DTO.UserResource;
import com.madhav.service.impl.StoreService;

@RestController
@RequestMapping("/api")
public class StoreController {

	@Autowired
	StoreService storeService;

	@PostMapping("/store/register")
	public Optional<StoreResource> createStore(@RequestBody StoreResource storeResource, @RequestBody UserResource userResource) {
		return storeService.createStore(storeResource, userResource);
	}

	@GetMapping("/store/{id}")
	public Optional<StoreResource> getStoreById(@PathVariable UUID id) throws ApiException {
		return storeService.getStoreById(id);
	}

	@GetMapping("/stores")
	public List<StoreResource> getAllStores() throws ApiException {
		return storeService.getAllStores();
	}

	@GetMapping("/storeByAdmin")
	public Optional<StoreResource> getStoreByAdmin() throws ApiException {
		return storeService.getStoreByAdmin();
	}

	@PutMapping("/store/update/{id}")
	public Optional<StoreResource> updateStore(@PathVariable UUID id, @RequestBody StoreResource storeResource) throws ApiException {
		return storeService.updateStore(id, storeResource);
	}

	@DeleteMapping("/store/delete/{id}")
	public void deleteStore(@PathVariable UUID id) throws ApiException {
		storeService.deleteStore(id);
	}

	@GetMapping("/storeByEmployee")
	public Optional<StoreResource> getStoreByEmployee() throws ApiException {
		return storeService.getStoreByEmployee();
	}

}
