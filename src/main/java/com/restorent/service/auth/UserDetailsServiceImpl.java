package com.restorent.service.auth;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restorent.entity.User;
import com.restorent.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Attempt to find user by email in the database
        Optional<User> optionalUser = userRepo.findFirstByEmail(email);
        
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Create UserDetails object using user details fetched from database
        User user = optionalUser.get();
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),  // Username
            user.getPassword(),  // Password
            new ArrayList<>()  // Authorities (empty for now)
        );
    }
}
