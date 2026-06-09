package com.personal_expenses_management.PEM.controller;

import com.personal_expenses_management.PEM.dto.RegistrationRequest;
import com.personal_expenses_management.PEM.entity.User;
import com.personal_expenses_management.PEM.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){

        try {
            User user = registrationService.register(request);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.IM_USED);
        }
    }
}
