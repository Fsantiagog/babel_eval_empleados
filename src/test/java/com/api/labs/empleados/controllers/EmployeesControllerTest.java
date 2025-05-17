package com.api.labs.empleados.controllers;

import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.models.EmployeeRequestModel;
import com.api.labs.empleados.models.enums.Sexo;
import com.api.labs.empleados.utilities.EmployeesUtilTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeesController.class)
@ExtendWith(MockitoExtension.class)
public class EmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private EmployeesDomain employeesDomain;


    @Test
    public void whenCreateAnEmployeeThenIsCreated() throws Exception {
        //give
        List<EmployeeRequestModel> employees = EmployeesUtilTest.buildOneRequests();
        String json = objectMapper.writeValueAsString(employees);
        //when
        mockMvc.perform(
                post("/empleados/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].nombreCompleto").value("Nombre Empleado"))
                .andExpect(jsonPath("$[0].apellidoPaterno").value("Apellido Paterno"))
                .andExpect(jsonPath("$[0].sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$[0].fechaNacimiento").value("1990-01-01"))
                .andExpect(jsonPath("$[0].puesto").value("Desarrollador"));
    }

    @Test
    public void whenCreateSomeEmployeesThenAreCreated() throws Exception {
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
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].nombreCompleto").value("Nombre Empleado"))
                .andExpect(jsonPath("$[0].apellidoPaterno").value("Apellido Paterno"))
                .andExpect(jsonPath("$[0].sexo").value(Sexo.MASCULINO.name()))
                .andExpect(jsonPath("$[0].fechaNacimiento").value("1990-01-01"))
                .andExpect(jsonPath("$[0].puesto").value("Desarrollador"));
    }

}
