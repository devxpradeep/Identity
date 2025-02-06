package com.microservice.identity.Dtos.Common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ApiError {
    private String message;
    private List<String> details;
}
