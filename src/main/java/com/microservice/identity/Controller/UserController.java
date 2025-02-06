package com.microservice.identity.Controller;

import com.microservice.identity.Dtos.Common.ApiResponse;
import com.microservice.identity.Dtos.Common.CommonResponse;
import com.microservice.identity.Dtos.Common.Metadata;
import com.microservice.identity.Dtos.Request.CreateUserRequest;
import com.microservice.identity.Dtos.Request.UpdateUserRequest;
import com.microservice.identity.Dtos.Response.UserResponse;
import com.microservice.identity.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/identity/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/{email}")
    public ResponseEntity<CommonResponse<UserDetails>> getByEmail(@RequestParam String email){
         return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        // Validate input parameters
        if (page < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Page must be >= 0 and pageSize must be > 0");
        }

        // Fetch data and total count
        List<UserResponse> users = userService.getAllUsers(page, pageSize);
        long totalUsers = userService.getUsersTotal();

        // Calculate metadata
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

        // Build response
        Metadata metadata = Metadata.builder()
                .totalItems(totalUsers)
                .pageSize(pageSize)
                .page(page)
                .totalPages(totalPages)
                .build();

        ApiResponse<List<UserResponse>> response = ApiResponse.<List<UserResponse>>builder()
                .data(users)
                .success(true)
                .metadata(metadata)
                .build();

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody CreateUserRequest createUserRequest
    ) {
        ApiResponse<UserResponse> result = userService.createUser(createUserRequest);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable String userId,
            @Valid @RequestBody UpdateUserRequest updateUserRequest
    ) {
        ApiResponse<UserResponse> result = userService.updateUser(userId, updateUserRequest);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(
            @PathVariable String userId
    ) {
        ApiResponse<UserResponse> result = userService.getUser(userId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> deleteUser(
            @PathVariable String userId
    ) {
        ApiResponse<UserResponse> result = userService.deleteUser(userId);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

}
