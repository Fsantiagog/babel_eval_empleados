package com.api.labs.empleados.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoResponseModel implements Serializable {
    private String id;
    private String nombreCompleto;
    private String apellidoPaterno;
    private String sexo;
    private String fechaNacimiento;
    private String puesto;

}
