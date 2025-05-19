package com.api.labs.empleados.controllers;

import com.api.labs.empleados.aop.AfterRegister;
import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.dtos.EmployeeUpdateRequestModel;
import com.api.labs.empleados.dtos.response.BadResponse;
import com.api.labs.empleados.dtos.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todos los empleados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empleados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron empleados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BadResponse.class)))
    })
    @GetMapping
    public ResponseEntity<Response<List<EmployeeResponseModel>>> findAll() {
        return Optional
                .of(employeesDomain.findAll())
                .map(buildSuccessResponse("Empleado(s) encontrado(s)"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener un empleado por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BadResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<EmployeeResponseModel>> findById(@PathVariable Long id) {
        return employeesDomain
                .findById(id)
                .map(buildSuccessResponse("Empleado encontrado"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear uno o varios empleados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado(s) creado(s)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BadResponse.class)))
    })
    @AfterRegister
    @PostMapping
    public ResponseEntity<Response<List<EmployeeResponseModel>>> create(@Valid @RequestBody List<EmployeeRequestModel> empleados) {
        return Optional.of(employeesDomain
                .createEmployees(empleados))
                .map(buildSuccessResponse("Empleado(s) creado(s)"))
                .map(employees -> ResponseEntity.created(URI.create("/empleados/v1")).body(employees))
                .orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Actualizar empleado", description = "Actualiza la información de un empleado dado su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BadResponse.class))),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BadResponse.class)))
    })
    @AfterRegister
    @PutMapping("/{id}")
    public ResponseEntity<Response<EmployeeResponseModel>> update(@RequestBody @Valid EmployeeUpdateRequestModel empleado, @PathVariable Long id) {
        return employeesDomain
                .updateById(id, empleado)
                .map(buildSuccessResponse("Empleado actualizado"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar empleado", description = "Elimina un empleado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empleado eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @AfterRegister
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeesDomain.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
