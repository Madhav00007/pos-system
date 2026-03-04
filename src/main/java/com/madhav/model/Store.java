package com.madhav.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.madhav.enums.StoreStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	
	@Column(nullable = false, unique = false)
	String brand;

	@OneToOne
	User storeAdmin;
	
	@Column(nullable = false, unique = false)
	LocalDateTime createdAt;
	
	@Column(nullable = false, unique = false)
	LocalDateTime updatedAt;
	
	@Column(nullable = true, unique = false)
	String description;
	
	@Column(nullable = false, unique = false)
	String storeType;
	
	@Column(nullable = false, unique = false)
	StoreStatus status;
	
	@Column(nullable = false, unique = false)
    StoreContact contact;
	
	@Column(nullable = false)
	private LocalDateTime lastLogin;

//	public UUID getId() {
//		return id;
//	}
//
//	public void setId(UUID id) {
//		this.id = id;
//	}
//
//	public LocalDateTime getLastLogin() {
//		return lastLogin;
//	}
//
//	public void setLastLogin(LocalDateTime lastLogin) {
//		this.lastLogin = lastLogin;
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
//	public User getStoreAdmin() {
//		return storeAdmin;
//	}
//
//	public void setStoreAdmin(User storeAdmin) {
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
//	public StoreContact getContact() {
//		return contact;
//	}
//
//	public void setContact(StoreContact contact) {
//		this.contact = contact;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(brand, contact, createdAt, description, id, status, storeAdmin, storeType, updatedAt);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Store other = (Store) obj;
//		return Objects.equals(brand, other.brand) && Objects.equals(contact, other.contact)
//				&& Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
//				&& Objects.equals(id, other.id) && status == other.status
//				&& Objects.equals(storeAdmin, other.storeAdmin) && Objects.equals(storeType, other.storeType)
//				&& Objects.equals(updatedAt, other.updatedAt);
//	}

	
	
	
}
