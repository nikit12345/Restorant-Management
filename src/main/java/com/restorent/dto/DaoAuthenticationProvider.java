package com.restorent.dto;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DaoAuthenticationProvider extends org.springframework.security.authentication.dao.DaoAuthenticationProvider{
	
	 public DaoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
	        this.setUserDetailsService(userDetailsService);
	        this.setPasswordEncoder(passwordEncoder);
	    }
	
public DaoAuthenticationProvider() {}
}
