package com.personal_expenses_management.PEM.dto;

import com.personal_expenses_management.PEM.entity.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAccountResponse {

    int userId;
    int accountId;
    String accountName;
    double balance;

    public AddAccountResponse(Account account){
        this.accountName = account.getAccountName();
        this.userId = account.getUser().getId();
        this.accountId = account.getId();
        this.balance = account.getBalance();
    }
}
