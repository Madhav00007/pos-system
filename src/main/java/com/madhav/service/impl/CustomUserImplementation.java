package com.madhav.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.madhav.configuration.JwtProvider;
import com.madhav.exceptions.ApiException;
import com.madhav.model.User;
import com.madhav.repository.UserRepository;

//import com.stripe.exception.ApiException;

@Service
public class CustomUserImplementation implements UserDetailsService {

    private final JwtProvider jwtProvider;
	
	@Autowired
	UserRepository userRepository;

    CustomUserImplementation(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username);
		if(user == null) {
//			throw new ApiException("User not found", user);
			throw new UsernameNotFoundException(username);
		}
		
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
		Collection<GrantedAuthority> authority = Collections.singleton(grantedAuthority);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authority);
	}
	
	

}
