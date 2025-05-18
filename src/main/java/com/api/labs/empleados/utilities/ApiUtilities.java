package com.api.labs.empleados.utilities;

import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.dtos.response.BadResponse;
import com.api.labs.empleados.dtos.response.Response;

import java.util.List;
import java.util.function.Function;

public final class ApiUtilities {
    private ApiUtilities() {}

    public static <T> Response<T> buildBadResponse(String message) {
        return BadResponse
                .<T>builder()
                .success(false)
                .message(message)
                .timestamp(String.valueOf(System.currentTimeMillis()))
                .build();
    }


    public static <T> Function<T, Response<T>> buildSuccessResponse(String message) {
        return foundData -> Response
                .<T>builder()
                .success(true)
                .message(message)
                .timestamp(String.valueOf(System.currentTimeMillis()))
                .data(foundData)
                .build();
    }

}
