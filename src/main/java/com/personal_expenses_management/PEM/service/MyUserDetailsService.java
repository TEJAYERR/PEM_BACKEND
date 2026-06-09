package com.personal_expenses_management.PEM.service;

import com.personal_expenses_management.PEM.entity.User;
import com.personal_expenses_management.PEM.entity.UserPrincipals;
import com.personal_expenses_management.PEM.repository.UserRepo;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public MyUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.getUserByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        System.out.println("DB User: " + user.getName());
        System.out.println("DB Password: " + user.getPassword());

        return new UserPrincipals(user);
    }
}
