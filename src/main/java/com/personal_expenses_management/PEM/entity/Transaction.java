package com.personal_expenses_management.PEM.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "transactions")
@Setter
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String description;
    double transactionAmount;
    double amountBeforeTransaction;
    double amountAfterTransaction;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;
}
