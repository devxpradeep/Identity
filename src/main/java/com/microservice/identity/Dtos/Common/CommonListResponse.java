package com.microservice.identity.Dtos.Common;

import org.springframework.http.HttpStatus;

import java.util.List;

public class CommonListResponse<T> {
    List<T> response;
    String message;
    HttpStatus statusCode;
}
