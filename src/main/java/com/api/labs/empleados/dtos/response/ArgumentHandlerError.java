package com.api.labs.empleados.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArgumentHandlerError implements Serializable {
    private String field;
    private String message;
    private String rejectedValue;
}
