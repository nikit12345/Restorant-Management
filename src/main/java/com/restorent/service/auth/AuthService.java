package com.restorent.service.auth;

import com.restorent.dto.SignupRequest;

import com.restorent.dto.UserDto;

public interface AuthService {

	public UserDto createUser(SignupRequest signupRequest);

}
