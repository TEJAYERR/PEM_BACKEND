package com.personal_expenses_management.PEM.service;

import com.personal_expenses_management.PEM.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if(!authentication.isAuthenticated()){
            throw new RuntimeException("User not found");
        }

        return jwtService.generateTokenWithEmail(loginRequest.getEmail());
    }
}
