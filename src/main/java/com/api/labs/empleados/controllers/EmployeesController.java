package com.api.labs.empleados.controllers;

import com.api.labs.empleados.aop.AfterRegister;
import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.dtos.EmployeeUpdateRequestModel;
import com.api.labs.empleados.dtos.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.api.labs.empleados.utilities.ApiUtilities.buildSuccessResponse;

@RestController
@RequestMapping("/empleados/v1")
@RequiredArgsConstructor
@Validated
public class EmployeesController {

    private final EmployeesDomain employeesDomain;

    @GetMapping
    public ResponseEntity<Response<List<EmployeeResponseModel>>> findAll() {
        return Optional
                .of(employeesDomain.findAll())
                .map(buildSuccessResponse("Empleado(s) encontrado(s)"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<EmployeeResponseModel>> findById(@PathVariable Long id) {
        return employeesDomain
                .findById(id)
                .map(buildSuccessResponse("Empleado encontrado"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @AfterRegister
    @PostMapping
    public ResponseEntity<Response<List<EmployeeResponseModel>>> create(@Valid @RequestBody List<EmployeeRequestModel> empleados) {
        return Optional.of(employeesDomain
                .createEmployees(empleados))
                .map(buildSuccessResponse("Empleado(s) creado(s)"))
                .map(employees -> ResponseEntity.created(URI.create("/empleados/v1")).body(employees))
                .orElse(ResponseEntity.badRequest().build());
    }

    @AfterRegister
    @PutMapping("/{id}")
    public ResponseEntity<Response<EmployeeResponseModel>> update(@RequestBody @Valid EmployeeUpdateRequestModel empleado, @PathVariable Long id) {
        return employeesDomain
                .updateById(id, empleado)
                .map(buildSuccessResponse("Empleado actualizado"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @AfterRegister
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeesDomain.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
