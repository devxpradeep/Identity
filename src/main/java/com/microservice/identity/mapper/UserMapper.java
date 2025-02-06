package com.microservice.identity.mapper;

import com.microservice.identity.Dtos.Request.CreateUserRequest;
import com.microservice.identity.Dtos.Request.UpdateUserRequest;
import com.microservice.identity.Dtos.Response.UserResponse;
import com.microservice.identity.Entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(UserEntity user);
    UserEntity toEntity(CreateUserRequest createUserRequest);
    UserEntity updateEntityFromDto(UpdateUserRequest updateUserRequest, @MappingTarget UserEntity user);
}
