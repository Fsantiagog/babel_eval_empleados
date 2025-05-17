package com.api.labs.empleados.dtos;

import com.api.labs.empleados.utilities.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestModel implements Serializable {
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Sexo sexo;
    private String fechaNacimiento;
    private Integer edad;
    private String puesto;

}
