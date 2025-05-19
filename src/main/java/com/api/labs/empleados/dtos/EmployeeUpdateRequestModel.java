package com.api.labs.empleados.dtos;

import com.api.labs.empleados.utilities.Sexo;;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateRequestModel implements Serializable {
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
    private String primerNombre;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
    private String segundoNombre;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")
    private String apellidoPaterno;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")
    private String apellidoMaterno;
    private Sexo sexo;
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$")
    private String fechaNacimiento;
    private Integer edad;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
    private String puesto;

}
