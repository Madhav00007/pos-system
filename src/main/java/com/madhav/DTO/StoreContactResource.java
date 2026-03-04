package com.madhav.DTO;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class StoreContactResource {
	
	@Email(message = "email should be valid")
	String email;
	
	String phoneNo;
	
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
