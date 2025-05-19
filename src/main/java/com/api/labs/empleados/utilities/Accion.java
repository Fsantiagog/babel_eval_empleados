package com.api.labs.empleados.utilities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Accion {
    CREAR_EMPLEADO("post"),
    MODIFICAR_EMPLEADO("put"),
    ELIMINAR_EMPLEADO("delete");
    private String method;

    public static Accion findByMethod(String method) {
        return Stream
                .of(values())
                .filter(x -> x.getMethod().equalsIgnoreCase(method))
                .findFirst()
                .orElse(null);
    }
}
