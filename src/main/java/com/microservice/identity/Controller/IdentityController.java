package com.microservice.identity.Controller;

import com.microservice.identity.Dtos.Common.CommonResponse;
import com.microservice.identity.Dtos.Request.LoginRequest;
import com.microservice.identity.Dtos.Request.SignupRequest;
import com.microservice.identity.Dtos.Response.LoginResponse;
import com.microservice.identity.Dtos.Response.SignupResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/identity")
public class IdentityController {

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(null);
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<SignupResponse>> signUp(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(null);
    }
}
