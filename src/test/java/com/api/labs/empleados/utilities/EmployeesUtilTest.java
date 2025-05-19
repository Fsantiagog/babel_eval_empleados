package com.api.labs.empleados.utilities;


import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.persistence.catalogs.Sexo;
import com.api.labs.empleados.persistence.entities.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.api.labs.empleados.constants.EmployeeConstants.*;
import static com.api.labs.empleados.constants.EmployeeConstants.FECHA_NACIMIENTO;

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
                .apellidoPaterno(APPELLIDO_PATERNO)
                .apellidoMaterno("ApellidoMaterno")
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento(FECHA_NACIMIENTO)
                .puesto(PUESTO)
                .build();
    }

    public static EmployeeRequestModel buildBadRequest() {
        return EmployeeRequestModel.builder()
                .puesto(PUESTO)
                .build();
    }

    public static EmployeeResponseModel buildResponse(){
        return EmployeeResponseModel
                .builder()
                .id(1L)
                .nombreCompleto(NOMBRE_COMPLETO)
                .apellidoPaterno(APPELLIDO_PATERNO)
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento(FECHA_NACIMIENTO)
                .puesto(PUESTO)
                .build();
    }

    public static EmployeeResponseModel buildUpdatedResponse(){
        return EmployeeResponseModel
                .builder()
                .id(1L)
                .nombreCompleto(NOMBRE_COMPLETO)
                .apellidoPaterno(APPELLIDO_PATERNO)
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento(FECHA_NACIMIENTO)
                .puesto("Nuevo Puesto")
                .build();
    }

    public static Employee buildEmployee(){
        return Employee
                .builder()
                .id(1L)
                .primerNombre("Nombre")
                .segundoNombre("Empleado")
                .apellidoPaterno(APPELLIDO_PATERNO)
                .apellidoMaterno(APPELLIDO_PATERNO)
                .sexo(Sexo.MASCULINO)
                .fechaNacimiento(LocalDate.parse(FECHA_NACIMIENTO, DateTimeFormatter.ofPattern(DateTimes.DATE_FORMAT)))
                .puesto(PUESTO)
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
