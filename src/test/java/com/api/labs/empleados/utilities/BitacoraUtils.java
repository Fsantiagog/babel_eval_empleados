package com.api.labs.empleados.utilities;

import com.api.labs.empleados.persistence.entities.Bitacora;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public final class BitacoraUtils {

    private BitacoraUtils() {}

    public static List<Bitacora> buildSomeBitacoras(){
        return Arrays.asList(buildBitacora(), buildBitacora(), buildBitacora(), buildBitacora());
    }

    public static Bitacora buildBitacora(){
        return Bitacora
                .builder()
                .id(1L)
                .accion(Accion.CREAR_EMPLEADO)
                .agent("agent")
                .entidad("entidad")
                .fechaHora(LocalDateTime.now())
                .build();
    }
}
