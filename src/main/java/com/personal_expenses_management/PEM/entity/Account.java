package com.personal_expenses_management.PEM.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity(name = "accounts")
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String accountName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    List<Transaction> transactions;

    double balance;


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", userId=" + user.getId() +
                ", transactions=" + transactions +
                ", balance=" + balance +
                '}';
    }
}
