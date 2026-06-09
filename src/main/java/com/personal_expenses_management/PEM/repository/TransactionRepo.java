package com.personal_expenses_management.PEM.repository;

import com.personal_expenses_management.PEM.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
