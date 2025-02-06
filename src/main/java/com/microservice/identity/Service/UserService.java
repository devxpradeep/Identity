package com.microservice.identity.Service;

import com.microservice.identity.Dtos.Common.ApiResponse;
import com.microservice.identity.Dtos.Common.CommonResponse;
import com.microservice.identity.Dtos.Request.CreateUserRequest;
import com.microservice.identity.Dtos.Request.UpdateUserRequest;
import com.microservice.identity.Dtos.Response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    CommonResponse<UserDetails> getUserByEmail(String email);
    UserDetailsService userDetailsService();
    List<UserResponse> getAllUsers(int page, int pageSize);
    long getUsersTotal();
    ApiResponse<UserResponse> createUser(CreateUserRequest createUserRequest);
    ApiResponse<UserResponse> getUser(String userId);
    ApiResponse<UserResponse> updateUser(String userId, UpdateUserRequest updateUserRequest);
    ApiResponse<UserResponse> deleteUser(String userId);
}
