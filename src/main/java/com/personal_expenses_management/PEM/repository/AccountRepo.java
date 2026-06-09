package com.personal_expenses_management.PEM.repository;

import com.personal_expenses_management.PEM.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepo extends JpaRepository<Account, Integer> {

    Account getAccountById(int id);
}
