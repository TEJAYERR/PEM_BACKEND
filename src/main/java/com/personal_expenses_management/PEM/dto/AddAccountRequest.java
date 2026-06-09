package com.personal_expenses_management.PEM.dto;

import com.personal_expenses_management.PEM.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAccountRequest {
    String accountName;
    double balance;
}
