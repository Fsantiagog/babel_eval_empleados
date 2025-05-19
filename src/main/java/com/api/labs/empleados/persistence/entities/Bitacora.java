package com.api.labs.empleados.persistence.entities;

import com.api.labs.empleados.utilities.Accion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bitacora", schema = "empleados")
@Entity
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Accion accion;
    private String entidad;
    private String agent;
    @CreationTimestamp
    private LocalDateTime fechaHora;
}
