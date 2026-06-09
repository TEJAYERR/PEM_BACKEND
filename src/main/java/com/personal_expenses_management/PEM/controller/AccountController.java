package com.personal_expenses_management.PEM.controller;

import com.personal_expenses_management.PEM.dto.AddAccountRequest;
import com.personal_expenses_management.PEM.entity.UserPrincipals;
import com.personal_expenses_management.PEM.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts(Authentication authentication){

        UserPrincipals userPrincipals = (UserPrincipals) authentication.getPrincipal();
        return new ResponseEntity<>(accountService.getAllAccounts(userPrincipals.getUser()),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAccount(Authentication authentication, @RequestBody AddAccountRequest addAccountRequest){

        UserPrincipals userPrincipals = (UserPrincipals) authentication.getPrincipal();
        System.out.println("Entered the add controller");
        return new ResponseEntity<>(accountService.addAccount(userPrincipals.getUser(), addAccountRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(Authentication authentication, @PathVariable int id){

        UserPrincipals userPrincipals = (UserPrincipals) authentication.getPrincipal();
        return new ResponseEntity<>(accountService.deleteAccount(userPrincipals.getUser(), id), HttpStatus.OK);
    }
}
