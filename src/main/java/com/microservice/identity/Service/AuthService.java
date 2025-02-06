package com.microservice.identity.Service;

import com.microservice.identity.Dtos.Common.ApiResponse;
import com.microservice.identity.Dtos.Request.LoginRequest;
import com.microservice.identity.Dtos.Request.SignupRequest;
import com.microservice.identity.Dtos.Response.JwtAuthenticationResponse;

public interface AuthService {
    ApiResponse<JwtAuthenticationResponse> signup(SignupRequest request);
    ApiResponse<JwtAuthenticationResponse> login(LoginRequest request);
}
