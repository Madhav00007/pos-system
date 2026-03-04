package com.madhav.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.madhav.enums.UserRole;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false, unique = false)
	private String fullName;
	
	@Column(nullable = false, unique = true)
	@Email(message = "Email should be valid..")
	private String email;
	
	@Column(nullable = true)
	private String phone;
	
	@Column(nullable = false)
	private UserRole role;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	@Column(nullable = false)
	private LocalDateTime lastLogin;

	@ManyToOne(fetch = FetchType.LAZY)
	Store store;
}
