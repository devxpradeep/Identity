package com.microservice.identity.Dtos.Common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class CommonResponse<T> {
    T response;
    String message;
    HttpStatus statusCode;
}
