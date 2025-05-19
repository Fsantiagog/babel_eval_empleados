package com.api.labs.empleados.utilities;


import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.persistence.entities.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static List<EmployeeRequestModel> buildOneBadRequests(){
        return Collections.singletonList(buildBadRequest());
    }

    public static EmployeeRequestModel buildOneRequest() {
        return EmployeeRequestModel.builder()
                .primerNombre("Nombre")
                .segundoNombre("Empleado")
                .apellidoPaterno("ApellidoPaterno")
                .apellidoMaterno("ApellidoMaterno")
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento("01-01-2000")
                .puesto("Desarrollador")
                .build();
    }

    public static EmployeeRequestModel buildBadRequest() {
        return EmployeeRequestModel.builder()
                .puesto("Desarrollador")
                .build();
    }

    public static EmployeeResponseModel buildResponse(){
        return EmployeeResponseModel
                .builder()
                .id(1L)
                .nombreCompleto("Nombre Empleado")
                .apellidoPaterno("ApellidoPaterno")
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento("01-01-2000")
                .puesto("Desarrollador")
                .build();
    }

    public static EmployeeResponseModel buildUpdatedResponse(){
        return EmployeeResponseModel
                .builder()
                .id(1L)
                .nombreCompleto("Nombre Empleado")
                .apellidoPaterno("ApellidoPaterno")
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento("01-01-2000")
                .puesto("Nuevo Puesto")
                .build();
    }

    public static Employee buildEmployee(){
        return Employee
                .builder()
                .id(1L)
                .primerNombre("Nombre")
                .segundoNombre("Empleado")
                .apellidoPaterno("ApellidoPaterno")
                .apellidoMaterno("ApellidoPaterno")
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento(LocalDate.parse("01-01-2000", DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .puesto("Desarrollador")
                .build();
    }

    public static List<EmployeeResponseModel> buildOneResponse(){
        return List.of(buildResponse());
    }
    public static List<EmployeeResponseModel> buildSomeResponse(){
        return Arrays.asList(buildResponse(), buildResponse(), buildResponse(), buildResponse());
    }
    public static List<Employee> buildSomeEmployees(){
        return Arrays.asList(buildEmployee(), buildEmployee(), buildEmployee(), buildEmployee());
    }
}
