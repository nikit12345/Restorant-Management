package com.restorent.service.auth;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.restorent.dto.SignupRequest;
import com.restorent.dto.UserDto;
import com.restorent.entity.User;
import com.restorent.enums.UserRole;
import com.restorent.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        if (signupRequest.getPassword() == null || signupRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);

        User createdUser = userRepo.save(user);

        UserDto createdUserDto = new UserDto();
        createdUserDto.setId(createdUser.getId());
        createdUserDto.setEmail(createdUser.getEmail());
        createdUserDto.setName(createdUser.getName());
        createdUserDto.setPassword(createdUser.getPassword());

        return createdUserDto;
    }
}
