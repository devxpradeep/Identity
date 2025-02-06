package com.microservice.identity.Dtos.Common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse<T> {
    private boolean success;
    private HttpStatusCode statusCode;
    private T data;
    private ApiError apiError;
    private Metadata metadata;
}
