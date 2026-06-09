package com.personal_expenses_management.PEM.service;

import com.personal_expenses_management.PEM.dto.RegistrationRequest;
import com.personal_expenses_management.PEM.entity.User;
import com.personal_expenses_management.PEM.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepo userRepo;

    @Autowired
    public RegistrationService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public User register(RegistrationRequest registrationRequest){

        User user = userRepo.getUserByEmail(registrationRequest.getEmail());

        if(user != null){
            throw new IllegalArgumentException("User Name already exists");
        }

        User newUser = new User();
        newUser.setName(registrationRequest.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(registrationRequest.getPassword()));
        newUser.setEmail(registrationRequest.getEmail());


        userRepo.save(newUser);
        return newUser;
    }
}
