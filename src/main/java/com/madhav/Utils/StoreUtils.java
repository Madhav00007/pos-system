package com.madhav.Utils;

import com.madhav.DTO.StoreContactResource;
import com.madhav.DTO.StoreResource;
import com.madhav.DTO.UserResource;
import com.madhav.enums.StoreStatus;
import com.madhav.model.Store;
import com.madhav.model.StoreContact;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreUtils {
	
	@Autowired
	UserUtils userUtils;
	
	public Store convertStoreResourceIntoStore(StoreResource storeResource) {
		Store store = new Store();
		store.setBrand(storeResource.getBrand());
		store.setContact(convertStoreContactResourceIntoStoreContact(storeResource.getContact()));
		store.setDescription(storeResource.getDescription());
		store.setStoreAdmin(userUtils.convertUserResourceIntoUser(storeResource.getStoreAdmin()));
		store.setStoreType(storeResource.getStoreType());
		return store;
	}
	
	public StoreContactResource convertStoreContactIntoStoreContactResource(StoreContact storeContact) {
		StoreContactResource storeConatctResource = new StoreContactResource();
		storeConatctResource.setAddress(storeContact.getAddress());
		storeConatctResource.setEmail(storeContact.getEmail());
		storeConatctResource.setPhoneNo(storeContact.getPhoneNo());
		return storeConatctResource;
	}
	
	public StoreContact convertStoreContactResourceIntoStoreContact(StoreContactResource storeContactResource) {
		StoreContact storeConatct = new StoreContact();
		storeConatct.setAddress(storeContactResource.getAddress());
		storeConatct.setEmail(storeContactResource.getEmail());
		storeConatct.setPhoneNo(storeContactResource.getPhoneNo());
		return storeConatct;
	}

	public Store convertStoreResourceIntoStore(StoreResource storeResource, UserResource userResource) {
		Store store = new Store();
		store.setBrand(storeResource.getBrand());
		store.setContact(convertStoreContactResourceIntoStoreContact(storeResource.getContact()));
		store.setDescription(storeResource.getDescription());
		store.setStoreAdmin(userUtils.convertUserResourceIntoUser(userResource));
		store.setStoreType(storeResource.getStoreType());
		store.setStatus(StoreStatus.PENDING);
		return store;
	}

	public StoreResource convertStoreIntoStoreResource(Store store) {
		if (store == null) {
			return null;
		}
		StoreResource storeResource = new StoreResource();
		storeResource.setId(store.getId());
		storeResource.setBrand(store.getBrand());
		storeResource.setCreatedAt(store.getCreatedAt());
		storeResource.setUpdatedAt(store.getUpdatedAt());
		storeResource.setLastLogin(store.getLastLogin());
		storeResource.setDescription(store.getDescription());
		storeResource.setStoreType(store.getStoreType());
		storeResource.setStatus(store.getStatus());
		storeResource.setStoreAdmin(userUtils.convertUserIntoUserResource(store.getStoreAdmin()));
		storeResource.setContact(convertStoreContactIntoStoreContactResource(store.getContact()));
		return storeResource;
	}


}
