package com.api.labs.empleados.utilities;


import com.api.labs.empleados.models.EmpleadoRequestModel;
import com.api.labs.empleados.models.enums.Sexo;

public final class EmpleadosUtilTest {

    private EmpleadosUtilTest() {}

    public static EmpleadoRequestModel buildOneRequest() {
        return EmpleadoRequestModel.builder()
                .primerNombre("Nombre")
                .segundoNombre("Empleado")
                .apellidoPaterno("Apellido Paterno")
                .apellidoMaterno("Apellido Materno")
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento("1990-01-01")
                .puesto("Desarrollador")
                .build();
    }
}
