package com.restorent.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restorent.dto.AuthenticationRequest;
import com.restorent.dto.AuthenticationResponse;
import com.restorent.dto.SignupRequest;
import com.restorent.dto.UserDto;
import com.restorent.entity.User;
import com.restorent.repository.UserRepo;
import com.restorent.service.auth.AuthService;
import com.restorent.service.auth.UserDetailsServiceImpl;
import com.restorent.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://13.201.59.6:6063")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        
        System.out.println("Received SignupRequest: " + signupRequest);

        if (signupRequest.getPassword() == null || signupRequest.getPassword().isEmpty()) {
            return new ResponseEntity<>("Password cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        UserDto userDto = authService.createUser(signupRequest);

        if (userDto == null) {
            return new ResponseEntity<>("User not created, please try again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        } catch (DisabledException d) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not active");
            return null;
        }

        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        Optional<User> optional = userRepo.findFirstByEmail(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optional.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optional.get().getUserRole());
            authenticationResponse.setUserId(optional.get().getId());
        }
        return authenticationResponse;
    }
    
}
