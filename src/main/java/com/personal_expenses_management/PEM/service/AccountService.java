package com.personal_expenses_management.PEM.service;

import com.personal_expenses_management.PEM.dto.AddAccountRequest;
import com.personal_expenses_management.PEM.dto.AddAccountResponse;
import com.personal_expenses_management.PEM.entity.Account;
import com.personal_expenses_management.PEM.entity.User;
import com.personal_expenses_management.PEM.repository.AccountRepo;
import com.personal_expenses_management.PEM.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepo accountRepo;
    private final UserRepo userRepo;

    public AccountService(AccountRepo accountRepo, UserRepo userRepo){

        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
    }

    public List<Account> getAllAccounts(User user) {

        User managedUser = userRepo.getUserById(user.getId());
        List<Account> accounts = managedUser.getAccounts();
        return accounts;
    }

    public AddAccountResponse addAccount(User user, AddAccountRequest addAccountRequest) {

        User managedUser = userRepo.findById(user.getId()).orElseThrow();

        Account newAccount = new Account();
        newAccount.setAccountName(addAccountRequest.getAccountName());
        newAccount.setUser(managedUser);
        newAccount.setBalance(addAccountRequest.getBalance());

        if(managedUser.getAccounts() == null){
            managedUser.setAccounts(new ArrayList<>());
        }

        accountRepo.save(newAccount);
        return new AddAccountResponse(newAccount);
    }

    public Account deleteAccount(User user, int id) {

        Account account = accountRepo.getAccountById(id);

        if(account == null){
            throw new RuntimeException("No account exist with the id");
        }

        accountRepo.delete(account);
        return account;
    }
}
