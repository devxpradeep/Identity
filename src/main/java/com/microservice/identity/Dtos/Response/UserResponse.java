package com.microservice.identity.Dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    String userId;
    String email;
    String firstName;
    String lastName;
}
