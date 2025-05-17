package com.api.labs.empleados.controllers;

import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empleados/v1")
public class EmployeesController {


    @PostMapping
    public ResponseEntity<List<EmployeeResponseModel>> createEmpleado(@RequestBody List<EmployeeRequestModel> empleados) {
        return ResponseEntity.created(URI.create("/empleados/v1")).body(
                empleados.stream()
                        .map(empleado -> EmployeeResponseModel.builder()
                                .id(1)
                                .nombreCompleto(empleado.getPrimerNombre() + " " + empleado.getSegundoNombre())
                                .apellidoPaterno(empleado.getApellidoPaterno())
                                .sexo(empleado.getSexo())
                                .fechaNacimiento(empleado.getFechaNacimiento())
                                .puesto(empleado.getPuesto())
                                .build())
                        .toList()
        );
    }
}
