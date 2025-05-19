package com.api.labs.empleados.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String method;
    private String uri;
    private String message;
    private T data;
    private Boolean success;
    private String timestamp;
}
