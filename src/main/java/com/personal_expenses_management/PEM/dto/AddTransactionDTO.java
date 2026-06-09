package com.personal_expenses_management.PEM.dto;

import com.personal_expenses_management.PEM.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTransactionDTO {

    String description;
    double transactionAmount;
    TransactionType transactionType;
}
