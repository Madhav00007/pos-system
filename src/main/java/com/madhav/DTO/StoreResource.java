package com.madhav.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import com.madhav.enums.StoreStatus;
import com.madhav.model.StoreContact;
import com.madhav.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StoreResource extends ApiResponse{
	
	UUID id;

	String brand;

	UserResource storeAdmin;

	LocalDateTime createdAt;

	LocalDateTime updatedAt;
	
	LocalDateTime lastLogin;

	String description;

	String storeType;

	StoreStatus status;

    StoreContactResource contact;

//	public String getMessage() {
//		return message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	public String getJwtToken() {
//		return jwtToken;
//	}
//
//	public void setJwtToken(String jwtToken) {
//		this.jwtToken = jwtToken;
//	}
//
//	public UUID getId() {
//		return id;
//	}
//
//	public void setId(UUID id) {
//		this.id = id;
//	}
//
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
//
//
//	public LocalDateTime getLastLogin() {
//		return lastLogin;
//	}
//
//	public void setLastLogin(LocalDateTime lastLogin) {
//		this.lastLogin = lastLogin;
//	}
//
//	public UserResource getStoreAdmin() {
//		return storeAdmin;
//	}
//
//	public void setStoreAdmin(UserResource storeAdmin) {
//		this.storeAdmin = storeAdmin;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getStoreType() {
//		return storeType;
//	}
//
//	public void setStoreType(String storeType) {
//		this.storeType = storeType;
//	}
//
//	public StoreStatus getStatus() {
//		return status;
//	}
//
//	public void setStatus(StoreStatus status) {
//		this.status = status;
//	}
//
//	public StoreContactResource getContact() {
//		return contact;
//	}
//
//	public void setContact(StoreContactResource contact) {
//		this.contact = contact;
//	}
    
    

}
