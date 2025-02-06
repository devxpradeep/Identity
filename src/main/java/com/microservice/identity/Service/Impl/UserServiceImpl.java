package com.microservice.identity.Service.Impl;

import com.microservice.identity.Dtos.Common.ApiResponse;
import com.microservice.identity.Dtos.Common.CommonResponse;
import com.microservice.identity.Dtos.Request.CreateUserRequest;
import com.microservice.identity.Dtos.Request.UpdateUserRequest;
import com.microservice.identity.Dtos.Response.UserResponse;
import com.microservice.identity.Entity.UserEntity;
import com.microservice.identity.Repository.UserRepository;
import com.microservice.identity.Service.UserService;
import com.microservice.identity.builders.CustomResponseBuilder;
import com.microservice.identity.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CustomResponseBuilder responseBuilder;

    @Override
    public CommonResponse<UserDetails> getUserByEmail(String email) {
        UserDetails userDetails = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return CommonResponse.<UserDetails>builder()
                .response(userDetails)
                .statusCode(HttpStatus.OK)
                .message("Success")
                .build();

    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public List<UserResponse> getAllUsers(int page, int pageSize) {
        return userRepository.findAll(PageRequest.of(page, pageSize))
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public long getUsersTotal() {
        return userRepository.count();
    }

    @Override
    public ApiResponse<UserResponse> createUser(CreateUserRequest createUserRequest) {
        try {
            UserEntity user = userMapper.toEntity(createUserRequest);
            user = userRepository.save(user);
            UserResponse userResponse = userMapper.toDto(user);
            return responseBuilder.buildSuccessResponse(userResponse);
        } catch (Exception ex) {
            //log.error("Error creating user: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, List.of(ex.getMessage()));
        }
    }

    @Override
    public ApiResponse<UserResponse> getUser(String userId) {
        try {
            Optional<UserEntity> userFromDb = userRepository.findById(userId);
            if (userFromDb.isPresent()) {
                UserEntity user = userFromDb.get();
                UserResponse userResponse = userMapper.toDto(user);
                return responseBuilder.buildSuccessResponse(userResponse);
            } else {
                return responseBuilder.buildNotFoundResponse();
            }
        } catch (Exception ex) {
            //log.error("Error creating user: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, List.of(ex.getMessage()));
        }
    }

    @Override
    public ApiResponse<UserResponse> updateUser(String userId, UpdateUserRequest updateUserRequest) {
        try {
            Optional<UserEntity> userFromDb = userRepository.findById(userId);
            if (userFromDb.isPresent()) {
                UserEntity user = userFromDb.get();
                UserEntity updated = userMapper.updateEntityFromDto(updateUserRequest, user);
                userRepository.save(updated);
                UserResponse userResponse = userMapper.toDto(updated);
                return responseBuilder.buildSuccessResponse(userResponse);
            } else {
                return responseBuilder.buildNotFoundResponse();
            }
        } catch (Exception ex) {
            //log.error("Error creating UserEntity: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, List.of(ex.getMessage()));
        }
    }

    @Override
    public ApiResponse<UserResponse> deleteUser(String userId) {
        try {
            Optional<UserEntity> userFromDb = userRepository.findById(userId);
            if (userFromDb.isPresent()) {
                UserEntity user = userFromDb.get();
                userRepository.deleteById(userId);
                UserResponse userResponse = userMapper.toDto(user);
                return responseBuilder.buildSuccessResponse(userResponse);
            } else {
                return responseBuilder.buildNotFoundResponse();
            }
        } catch (Exception ex) {
            //log.error("Error creating UserEntity: {}", ex.getMessage(), ex);
            return responseBuilder.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, List.of(ex.getMessage()));
        }
    }
}
