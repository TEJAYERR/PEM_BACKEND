package com.personal_expenses_management.PEM.controller;

import com.personal_expenses_management.PEM.dto.LoginRequest;
import com.personal_expenses_management.PEM.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }
}
