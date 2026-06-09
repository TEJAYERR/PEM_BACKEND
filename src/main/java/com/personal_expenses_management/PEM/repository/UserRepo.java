package com.personal_expenses_management.PEM.repository;

import com.personal_expenses_management.PEM.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User getUserByName(String name);

    User getUserByEmail(String email);

    User getUserById(int id);
}
