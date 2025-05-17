package com.api.labs.empleados.controllers;

import com.api.labs.empleados.domains.EmpleadosDomain;
import com.api.labs.empleados.models.EmpleadoRequestModel;
import com.api.labs.empleados.models.enums.Sexo;
import com.api.labs.empleados.utilities.EmpleadosUtilTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmpleadosController.class)
@ExtendWith(MockitoExtension.class)
public class EmpleadosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private EmpleadosDomain empleadosDomain;


    @Test
    public void whenCreateAnEmployerThenIsCreated() throws Exception {
        //give
        EmpleadoRequestModel empleado = EmpleadosUtilTest.buildOneRequest();
        String json = objectMapper.writeValueAsString(List.of(empleado));
        System.out.println(json);
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

}
