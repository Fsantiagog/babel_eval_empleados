package com.api.labs.empleados.utilities;


import com.api.labs.empleados.models.EmployeeRequestModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class EmployeesUtilTest {

    private EmployeesUtilTest() {}

    public static List<EmployeeRequestModel> buildSomeRequests(){
        return Arrays.asList(buildOneRequest(), buildOneRequest(), buildOneRequest(), buildOneRequest());
    }

    public static List<EmployeeRequestModel> buildOneRequests(){
        return Collections.singletonList(buildOneRequest());
    }

    public static EmployeeRequestModel buildOneRequest() {
        return EmployeeRequestModel.builder()
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
