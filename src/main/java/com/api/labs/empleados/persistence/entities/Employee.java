package com.api.labs.empleados.persistence.entities;

import com.api.labs.empleados.persistence.catalogs.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empleados", schema = "empleados")
@Entity(name = "empleados")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private String puesto;

}
