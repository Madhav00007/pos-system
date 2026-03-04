package com.madhav.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class StoreContact {
	
	@Email(message = "email should be valid")
	@Column(nullable = false, unique = true)
	String email;
	
	@Column(nullable = false, unique = true)
	String phoneNo;
	
	@Column(nullable = false, unique = false)
	String address;

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPhoneNo() {
//		return phoneNo;
//	}
//
//	public void setPhoneNo(String phoneNo) {
//		this.phoneNo = phoneNo;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
	
	

}
