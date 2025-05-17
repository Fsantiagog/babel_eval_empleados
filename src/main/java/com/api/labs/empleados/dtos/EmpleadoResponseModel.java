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
public class EmpleadoResponseModel implements Serializable {
    private Integer id;
    private String nombreCompleto;
    private String apellidoPaterno;
    private Sexo sexo;
    private String fechaNacimiento;
    private String puesto;

}
