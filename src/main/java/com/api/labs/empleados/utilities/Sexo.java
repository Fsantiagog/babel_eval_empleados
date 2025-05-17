package com.api.labs.empleados.utilities;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Sexo {
    MASCULINO,
    FEMENINO,
    SIN_ESPECIFICAR;

    public static Sexo fromValue(String value) {
        return Stream
                .of(values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(SIN_ESPECIFICAR);
    }
}
