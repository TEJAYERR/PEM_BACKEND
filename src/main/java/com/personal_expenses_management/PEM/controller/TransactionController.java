package com.personal_expenses_management.PEM.controller;

import com.personal_expenses_management.PEM.dto.AddTransactionDTO;
import com.personal_expenses_management.PEM.entity.Transaction;
import com.personal_expenses_management.PEM.entity.UserPrincipals;
import com.personal_expenses_management.PEM.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts/{accountId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<?> getTransactions(Authentication authentication,
                                             @PathVariable int accountId){

        UserPrincipals userPrincipals = (UserPrincipals) authentication.getPrincipal();
        return new ResponseEntity<>(transactionService.getTransactions(userPrincipals.getUser(), accountId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addTransaction(Authentication authentication,
                                            @PathVariable int accountId,
                                            @RequestBody AddTransactionDTO addTransactionDTO){

        UserPrincipals userPrincipals = (UserPrincipals) authentication.getPrincipal();
        return new ResponseEntity<>(transactionService.addTransaction(userPrincipals.getUser(), addTransactionDTO, accountId), HttpStatus.OK);
    }
}
