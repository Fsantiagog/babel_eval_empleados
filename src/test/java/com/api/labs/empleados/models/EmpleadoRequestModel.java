package com.api.labs.empleados.models;

import com.api.labs.empleados.models.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoRequestModel implements Serializable {
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Sexo sexo;
    private String fechaNacimiento;
    private Integer edad;
    private String puesto;
}
