package com.microservice.identity.Controller;

import com.microservice.identity.Dtos.Common.CommonResponse;
import com.microservice.identity.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/{email}")
    public ResponseEntity<CommonResponse<UserDetails>> getByEmail(@RequestParam String email){
         return ResponseEntity.ok(userService.getUserByEmail(email));
    }

}
