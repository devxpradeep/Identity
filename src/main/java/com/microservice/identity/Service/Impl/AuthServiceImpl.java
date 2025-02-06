package com.microservice.identity.Service.Impl;

import com.microservice.identity.Dtos.Common.ApiResponse;
import com.microservice.identity.Dtos.Request.LoginRequest;
import com.microservice.identity.Dtos.Request.SignupRequest;
import com.microservice.identity.Dtos.Response.JwtAuthenticationResponse;
import com.microservice.identity.Entity.Role;
import com.microservice.identity.Entity.UserEntity;
import com.microservice.identity.Repository.UserRepository;
import com.microservice.identity.Service.AuthService;
import com.microservice.identity.Service.JwtService;
import com.microservice.identity.builders.CustomResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomResponseBuilder responseBuilder;
    private final AuthenticationManager authenticationManager;
    @Override
    public ApiResponse<JwtAuthenticationResponse> signup(SignupRequest request) {
        try{
            var user = UserEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .role(Role.USER)
                    .password(passwordEncoder.encode(request.getPassword()))
                    .Enabled(true)
                    .build();
            userRepository.save(user);
            var jwt = jwtService.generateToken(user);
            return responseBuilder.buildSuccessResponse(JwtAuthenticationResponse.builder()
                    .token(jwt)
                    .build());
        }
        catch (Exception ex){
            //log.error("Error creating user: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, List.of(ex.getMessage()));
        }
    }

    @Override
    public ApiResponse<JwtAuthenticationResponse> login(LoginRequest request) {
        try {
            // Validate the user's email and fetch user details
            var user = userRepository.findByEmail(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));

            // Authenticate the user's credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Generate a JWT token for the authenticated user
            var jwt = jwtService.generateToken(user);

            // Build and return a success response with the token
            JwtAuthenticationResponse jwtResponse = JwtAuthenticationResponse.builder()
                    .token(jwt)
                    .build();
            return responseBuilder.buildSuccessResponse(jwtResponse);
        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            // Handle invalid credentials with a specific error response
            return responseBuilder.buildErrorResponse(
                    HttpStatus.UNAUTHORIZED,
                    List.of("Invalid email or password.")
            );
        } catch (Exception ex) {
            // Handle other exceptions with a generic error response
            //log.error("Error during login: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    List.of("An unexpected error occurred during login.")
            );
        }
    }

}
