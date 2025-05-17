package com.api.labs.empleados.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmpleadosController.class)
public class EmpleadosController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmpleadosDomain empleadosDomain;


    @Test
    public void whenCreateAnEmployerThenIsCreated() throws Exception {
        //give
        EmpleadoModel empleado = EmpleadosTestUtil.buildOne();
        String json = objectMapper.writeValueAsString(empleado);
        //when
        mockMvc.perform(
                post("/empleados/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(empleado.getId()))
                .andExpect(jsonPath("$.nombreCompleto").value(empleado.getNombreCompleto()))
                .andExpect(jsonPath("$.apellidoPaterno").value(empleado.getApellido()))
                .andExpect(jsonPath("$.sexo").value(empleado.getSexo()))
                .andExpect(jsonPath("$.fechaNacimiento").value(empleado.getFechaNacimiento()))
                .andExpect(jsonPath("$.puesto").value(empleado.getPuesto()));
        //veryfy
        Mockito
                .verify(empleadosDomain, Mockito.atLeastOnce())
                .createEmployers(Mockito.any(EmpleadoModel.class));
    }

}
