package com.microservice.identity.Dtos.Request;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
