package com.microservice.identity.Controller;

import com.microservice.identity.Dtos.Common.ApiResponse;
import com.microservice.identity.Dtos.Request.LoginRequest;
import com.microservice.identity.Dtos.Request.SignupRequest;
import com.microservice.identity.Dtos.Response.JwtAuthenticationResponse;
import com.microservice.identity.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/identity/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> login(@RequestBody LoginRequest loginRequest) {
        var response = authService.login(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> signUp(@RequestBody SignupRequest signupRequest){
        var response = authService.signup(signupRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
