package com.api.labs.empleados.controllers;

import com.api.labs.empleados.aop.AfterRegister;
import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.dtos.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/empleados/v1")
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesDomain employeesDomain;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseModel>> findAll() {
        return Optional
                .of(employeesDomain.findAll())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseModel> findById(@PathVariable Long id) {
        return employeesDomain
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @AfterRegister
    @PostMapping
    public ResponseEntity<List<EmployeeResponseModel>> create(@RequestBody List<EmployeeRequestModel> empleados) {
        return Optional.of(employeesDomain
                .createEmployeers(empleados))
                .map(employees -> ResponseEntity.created(URI.create("/empleados/v1"))
                        .body(employees))
                .orElse(ResponseEntity.badRequest().build());
    }

    @AfterRegister
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseModel> update(@PathVariable Long id, @RequestBody EmployeeRequestModel empleado) {
        return employeesDomain
                .update(id, empleado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @AfterRegister
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return employeesDomain
                .deleteById(id)
                .map(deleted -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
