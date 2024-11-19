package com.microservice.identity.Service;

import com.microservice.identity.Dtos.Common.CommonResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    CommonResponse<UserDetails> getUserByEmail(String email);
}
