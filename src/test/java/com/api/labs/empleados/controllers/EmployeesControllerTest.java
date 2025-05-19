package com.api.labs.empleados.controllers;

import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.utilities.EmployeesUtilTest;
import com.api.labs.empleados.utilities.Sexo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeesController.class)
public class EmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EmployeesDomain employeesDomain;


    @Test
    public void whenCreateAnEmployeeThenIsCreated() throws Exception {
        //give
        List<EmployeeRequestModel> employees = EmployeesUtilTest.buildOneRequests();
        String json = objectMapper.writeValueAsString(employees);
        //when
        Mockito
                .when(employeesDomain.createEmployees(Mockito.anyList()))
                .thenReturn(EmployeesUtilTest.buildOneResponse());
        mockMvc.perform(
                post("/empleados/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
        //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Empleado(s) creado(s)"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[0].id").exists())
                .andExpect(jsonPath("$.data[0].nombreCompleto").value("Nombre Empleado"))
                .andExpect(jsonPath("$.data[0].apellidoPaterno").value("ApellidoPaterno"))
                .andExpect(jsonPath("$.data[0].sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data[0].fechaNacimiento").value("01-01-2000"))
                .andExpect(jsonPath("$.data[0].puesto").value("Desarrollador"));
    }

    @Test
    public void whenCreateSomeEmployeesThenAreCreated() throws Exception {
        Mockito
                .when(employeesDomain.createEmployees(Mockito.anyList()))
                .thenReturn(EmployeesUtilTest.buildSomeResponse());
        //give
        List<EmployeeRequestModel> employees = EmployeesUtilTest.buildSomeRequests();
        String json = objectMapper.writeValueAsString(employees);
        //when
        mockMvc.perform(
                post("/empleados/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Empleado(s) creado(s)"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.length()").value(4))
                .andExpect(jsonPath("$.data[0].id").exists())
                .andExpect(jsonPath("$.data[0].nombreCompleto").value("Nombre Empleado"))
                .andExpect(jsonPath("$.data[0].apellidoPaterno").value("ApellidoPaterno"))
                .andExpect(jsonPath("$.data[0].sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data[0].fechaNacimiento").value("01-01-2000"))
                .andExpect(jsonPath("$.data[0].puesto").value("Desarrollador"));
    }


    @Test
    public void whenCreateAnEmployeeThenIsBadRequest() throws Exception {
        //give
        String json = "[{'Puesto':'BadRequest'}]";
        //when
        Mockito
                .when(employeesDomain.createEmployees(Mockito.anyList()))
                .thenReturn(EmployeesUtilTest.buildOneResponse());
        mockMvc.perform(
                        post("/empleados/v1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                //then
                .andExpect(status().isBadRequest())
                ;
    }


    @Test
    public void whenUpdateAnEmployeeThenIsUpdated() throws Exception {
        Mockito
                .when(employeesDomain.updateById(Mockito.anyLong(), Mockito.any()))
                .thenReturn(Optional.of(EmployeesUtilTest.buildResponse()));
        //give
        EmployeeRequestModel employee = EmployeesUtilTest.buildOneRequest();
        String json = objectMapper.writeValueAsString(employee);
        //when
        mockMvc.perform(
                put("/empleados/v1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Empleado actualizado"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.nombreCompleto").value("Nombre Empleado"))
                .andExpect(jsonPath("$.data.apellidoPaterno").value("ApellidoPaterno"))
                .andExpect(jsonPath("$.data.sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data.fechaNacimiento").value("01-01-2000"))
                .andExpect(jsonPath("$.data.puesto").value("Desarrollador"));
    }

    @Test
    public void whenUpdateAnEmployeeThenIsNotFound() throws Exception {
        Mockito
                .when(employeesDomain.updateById(Mockito.anyLong(), Mockito.any()))
                .thenReturn(Optional.empty());
        //give
        EmployeeRequestModel employee = EmployeesUtilTest.buildOneRequest();
        String json = objectMapper.writeValueAsString(employee);
        //when
        mockMvc.perform(
                        put("/empleados/v1/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenDeleteAnEmployeeThenIsDeleted() throws Exception {
        Mockito
                .doNothing()
                .when(employeesDomain)
                .deleteById(Mockito.anyLong());
        //give
        //when
        mockMvc.perform(
                        delete("/empleados/v1/1"))
                //then
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenFindByIdThenIsOk() throws Exception {
        //give
        Long id = 1L;
        //when
        Mockito
                .when(employeesDomain.findById(id))
                .thenReturn(Optional.of(EmployeesUtilTest.buildResponse()));
        mockMvc.perform(
                        get("/empleados/v1/1"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Empleado encontrado"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.nombreCompleto").value("Nombre Empleado"))
                .andExpect(jsonPath("$.data.apellidoPaterno").value("ApellidoPaterno"))
                .andExpect(jsonPath("$.data.sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data.fechaNacimiento").value("01-01-2000"))
                .andExpect(jsonPath("$.data.puesto").value("Desarrollador"));
    }

    @Test
    public void whenFindByIdThenIsNotFound() throws Exception {
        //give
        Long id = 1L;
        //when
        Mockito
                .when(employeesDomain.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(
                        get("/empleados/v1/1"))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenFindAllThenIsOk() throws Exception {
        //give
        //when
        Mockito
                .when(employeesDomain.findAll())
                .thenReturn(EmployeesUtilTest.buildSomeResponse());
        mockMvc.perform(
                        get("/empleados/v1"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Empleado(s) encontrado(s)"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.length()").value(4))
                .andExpect(jsonPath("$.data[0].id").exists())
                .andExpect(jsonPath("$.data[0].nombreCompleto").value("Nombre Empleado"))
                .andExpect(jsonPath("$.data[0].apellidoPaterno").value("ApellidoPaterno"))
                .andExpect(jsonPath("$.data[0].sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data[0].fechaNacimiento").value("01-01-2000"))
                .andExpect(jsonPath("$.data[0].puesto").value("Desarrollador"));
    }

}
