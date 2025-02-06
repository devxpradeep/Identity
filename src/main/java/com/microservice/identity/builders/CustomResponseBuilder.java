package com.microservice.identity.builders;

import com.microservice.identity.Dtos.Common.ApiError;
import com.microservice.identity.Dtos.Common.ApiResponse;
import com.microservice.identity.Utils.CommonMessageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomResponseBuilder {

    public <T> ApiResponse<T> buildSuccessResponse(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.OK)
                .data(data)
                .build();
    }

    public <T> ApiResponse<T> buildNotFoundResponse() {
        return ApiResponse.<T>builder()
                .success(true)
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
    }

    public <T> ApiResponse<T> buildErrorResponse(HttpStatusCode statusCode, List<String> details) {
        return ApiResponse.<T>builder()
                .success(false)
                .statusCode(statusCode)
                .apiError(ApiError.builder()
                        .message(CommonMessageUtils.SOMETHING_WENT_WRONG)
                        .details(details)
                        .build())
                .build();
    }

}
