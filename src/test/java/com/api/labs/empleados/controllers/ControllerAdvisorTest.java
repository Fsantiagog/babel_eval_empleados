package com.api.labs.empleados.controllers;

import com.api.labs.empleados.domains.EmployeesDomain;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.api.labs.empleados.utilities.EmployeesUtilTest.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EmployeesController.class)
@Import(ControllerAdvisor.class)
class ControllerAdvisorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeesDomain employeesDomain;

    @Test
    public void whenCretingSomeEmployees_thenExceptionHandler() throws Exception {
        //given
        String badRequest = "[{\"puesto\":\"BadRequest\"}]";
        //when
        mockMvc
                .perform(
                        post("/empleados/v1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badRequest)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Bad Request: Validación de campos"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.method").value("POST"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors[0].field").exists())
                .andExpect(jsonPath("$.errors[0].message").exists())
                .andExpect(jsonPath("$.errors[0].rejectedValue").exists())


        ;
    }

    @Test
    public void whenUpdatingSomeEmployees_thenExceptionHandler() throws Exception {
        //given
        Long id = 1L;
        String badRequest = "{\"puesto\":\"\"}";
        //when
        Mockito
                .when(employeesDomain.findById(id))
                .thenReturn(Optional.of(buildResponse()));
        mockMvc
                .perform(
                        put("/empleados/v1/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badRequest)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Bad Request: Validación de campos"))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.method").value("PUT"))
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors[0].field").exists())
                .andExpect(jsonPath("$.errors[0].message").exists())
                .andExpect(jsonPath("$.errors[0].rejectedValue").exists())


        ;
    }
}