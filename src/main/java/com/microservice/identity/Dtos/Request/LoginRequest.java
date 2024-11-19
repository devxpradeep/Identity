package com.microservice.identity.Dtos.Request;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
