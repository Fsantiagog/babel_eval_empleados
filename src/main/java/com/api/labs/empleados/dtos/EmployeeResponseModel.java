package com.api.labs.empleados.dtos;

import com.api.labs.empleados.persistence.catalogs.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseModel implements Serializable {
    private Long id;
    private String nombreCompleto;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Sexo sexo;
    private String fechaNacimiento;
    private String puesto;

}
