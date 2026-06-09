package com.personal_expenses_management.PEM.service;

import com.personal_expenses_management.PEM.dto.AddTransactionDTO;
import com.personal_expenses_management.PEM.entity.Account;
import com.personal_expenses_management.PEM.entity.Transaction;
import com.personal_expenses_management.PEM.entity.TransactionType;
import com.personal_expenses_management.PEM.entity.User;
import com.personal_expenses_management.PEM.repository.AccountRepo;
import com.personal_expenses_management.PEM.repository.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final AccountRepo accountRepo;

    public TransactionService(TransactionRepo transactionRepo, AccountRepo accountRepo){
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    public List<Transaction> getTransactions(User user, int accountId){

        Account account = accountRepo.getAccountById(accountId);
        if(account == null){
            throw new RuntimeException("No account exist");
        }

        return account.getTransactions();
    }

    @Transactional
    public Transaction addTransaction(User user, AddTransactionDTO addTransactionDTO, int accountId) {

        Account account = accountRepo.getAccountById(accountId);
        if(account == null) {
            throw new RuntimeException("No account exist");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(addTransactionDTO.getTransactionAmount());
        transaction.setAccount(account);
        transaction.setDescription(addTransactionDTO.getDescription());
        transaction.setAmountBeforeTransaction(account.getBalance());
        transaction.setTransactionType(addTransactionDTO.getTransactionType());

        if(addTransactionDTO.getTransactionType() == TransactionType.EXPENSE) {
            account.setBalance(account.getBalance() - addTransactionDTO.getTransactionAmount());

            if(account.getBalance() < addTransactionDTO.getTransactionAmount()){
                throw new RuntimeException("Transaction not possible");
            }
        }
        else
            account.setBalance(account.getBalance() + addTransactionDTO.getTransactionAmount());

        transaction.setAmountAfterTransaction(account.getBalance());

        transactionRepo.save(transaction);
        accountRepo.save(account);

        return transaction;
    }
}
