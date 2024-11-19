package com.microservice.identity.Dtos.Common;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class CommonResponse<T> {
    T response;
    String message;
    HttpStatus statusCode;
}
