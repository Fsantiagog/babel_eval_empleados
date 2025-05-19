package com.api.labs.empleados.controllers;

import com.api.labs.empleados.constants.URIConstants;
import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.utilities.EmployeesUtilTest;
import com.api.labs.empleados.persistence.catalogs.Sexo;
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

import static com.api.labs.empleados.constants.EmployeeConstants.*;
import static com.api.labs.empleados.constants.EmployeeConstants.FECHA_NACIMIENTO;
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
                post(URIConstants.EMPLOYEE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
        //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Empleado(s) creado(s)"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data[0].id").exists())
                .andExpect(jsonPath("$.data[0].nombreCompleto").value(NOMBRE_COMPLETO))
                .andExpect(jsonPath("$.data[0].apellidoPaterno").value(APPELLIDO_PATERNO))
                .andExpect(jsonPath("$.data[0].sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data[0].fechaNacimiento").value(FECHA_NACIMIENTO))
                .andExpect(jsonPath("$.data[0].puesto").value(PUESTO));
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
                post(URIConstants.EMPLOYEE_URI)
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
                .andExpect(jsonPath("$.data[0].nombreCompleto").value(NOMBRE_COMPLETO))
                .andExpect(jsonPath("$.data[0].apellidoPaterno").value(APPELLIDO_PATERNO))
                .andExpect(jsonPath("$.data[0].sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data[0].fechaNacimiento").value(FECHA_NACIMIENTO))
                .andExpect(jsonPath("$.data[0].puesto").value(PUESTO));
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
                        post(URIConstants.EMPLOYEE_URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                //then
                .andExpect(status().isBadRequest())
                ;
    }


    @Test
    public void whenUpdateAnEmployeeThenIsUpdated() throws Exception {
        //give
        Long id = 1L;
        Mockito
                .when(employeesDomain.updateById(Mockito.anyLong(), Mockito.any()))
                .thenReturn(Optional.of(EmployeesUtilTest.buildResponse()));
        //give
        EmployeeRequestModel employee = EmployeesUtilTest.buildOneRequest();
        String json = objectMapper.writeValueAsString(employee);
        //when
        mockMvc.perform(
                put(URIConstants.EMPLOYEE_URI_.formatted(id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Empleado actualizado"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.nombreCompleto").value(NOMBRE_COMPLETO))
                .andExpect(jsonPath("$.data.apellidoPaterno").value(APPELLIDO_PATERNO))
                .andExpect(jsonPath("$.data.sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data.fechaNacimiento").value(FECHA_NACIMIENTO))
                .andExpect(jsonPath("$.data.puesto").value(PUESTO));
    }

    @Test
    public void whenUpdateAnEmployeeThenIsNotFound() throws Exception {
        //give
        Long id = 1L;
        Mockito
                .when(employeesDomain.updateById(Mockito.anyLong(), Mockito.any()))
                .thenReturn(Optional.empty());
        //give
        EmployeeRequestModel employee = EmployeesUtilTest.buildOneRequest();
        String json = objectMapper.writeValueAsString(employee);
        //when
        mockMvc.perform(
                        put(URIConstants.EMPLOYEE_URI_.formatted(id))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenDeleteAnEmployeeThenIsDeleted() throws Exception {
        //give
        Long id = 1L;
        Mockito
                .doNothing()
                .when(employeesDomain)
                .deleteById(Mockito.anyLong());
        //give
        //when
        mockMvc.perform(
                        delete(URIConstants.EMPLOYEE_URI_.formatted(id)))
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
                        get(URIConstants.EMPLOYEE_URI_.formatted(id)))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Empleado encontrado"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.nombreCompleto").value(NOMBRE_COMPLETO))
                .andExpect(jsonPath("$.data.apellidoPaterno").value(APPELLIDO_PATERNO))
                .andExpect(jsonPath("$.data.sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data.fechaNacimiento").value(FECHA_NACIMIENTO))
                .andExpect(jsonPath("$.data.puesto").value(PUESTO));
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
                        get(URIConstants.EMPLOYEE_URI_.formatted(id)))
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
                        get(URIConstants.EMPLOYEE_URI))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Empleado(s) encontrado(s)"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.length()").value(4))
                .andExpect(jsonPath("$.data[0].id").exists())
                .andExpect(jsonPath("$.data[0].nombreCompleto").value(NOMBRE_COMPLETO))
                .andExpect(jsonPath("$.data[0].apellidoPaterno").value(APPELLIDO_PATERNO))
                .andExpect(jsonPath("$.data[0].sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$.data[0].fechaNacimiento").value(FECHA_NACIMIENTO))
                .andExpect(jsonPath("$.data[0].puesto").value(PUESTO));
    }

}
