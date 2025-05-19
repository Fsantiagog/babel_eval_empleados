package com.api.labs.empleados.dtos;

import com.api.labs.empleados.persistence.catalogs.Sexo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestModel implements Serializable {
    @NonNull
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
    private String primerNombre;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
    private String segundoNombre;
    @NotNull
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")
    private String apellidoPaterno;
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")
    private String apellidoMaterno;
    @NotNull
    private Sexo sexo;
    @NotNull
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$")
    private String fechaNacimiento;
    private Integer edad;
    @NonNull
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
    private String puesto;

}
