package com.personal_expenses_management.PEM.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    String email;
    String username;
    String password;

}
