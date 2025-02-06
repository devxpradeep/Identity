package com.microservice.identity.Dtos.Request;

import com.microservice.identity.Entity.Role;
import lombok.Data;

@Data
public class CreateUserRequest {
    String email;
    String firstName;
    String lastName;
    Role role;
}
