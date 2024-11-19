package com.microservice.identity.Service.Impl;

import com.microservice.identity.Dtos.Common.CommonResponse;
import com.microservice.identity.Repository.UserRepository;
import com.microservice.identity.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public CommonResponse<UserDetails> getUserByEmail(String email){
        UserDetails userDetails =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return CommonResponse.<UserDetails>builder()
                .response(userDetails)
                .statusCode(HttpStatus.OK)
                .message("Success")
                .build();

    }
}
