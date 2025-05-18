package com.api.labs.empleados.controllers;

import com.api.labs.empleados.aop.BitacoraAspect;
import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.models.EmployeeRequestModel;
import com.api.labs.empleados.utilities.EmployeesUtilTest;
import com.api.labs.empleados.utilities.Sexo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Mock
    private BitacoraAspect bitacoraAspect;

    @Test
    public void whenCreateAnEmployeeThenIsCreated() throws Exception {
        Mockito
                .when(employeesDomain.createEmployeers(Mockito.anyList()))
                .thenReturn(Arrays.asList(
                        EmployeeResponseModel
                                .builder()
                                .id(1L)
                                .nombreCompleto("Nombre Empleado")
                                .apellidoPaterno("Apellido Paterno")
                                .sexo(Sexo.MASCULINO)
                                .fechaNacimiento("1990-01-01")
                                .puesto("Desarrollador")
                                .build()
                ));
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
        Mockito
                .when(employeesDomain.createEmployeers(Mockito.anyList()))
                .thenReturn(Arrays.asList(
                        EmployeeResponseModel
                                .builder()
                                .id(1L)
                                .nombreCompleto("Nombre Empleado")
                                .apellidoPaterno("Apellido Paterno")
                                .sexo(Sexo.MASCULINO)
                                .fechaNacimiento("1990-01-01")
                                .puesto("Desarrollador")
                                .build(),
                        EmployeeResponseModel
                                .builder()
                                .id(1L)
                                .nombreCompleto("Nombre Empleado")
                                .apellidoPaterno("Apellido Paterno")
                                .sexo(Sexo.MASCULINO)
                                .fechaNacimiento("1990-01-01")
                                .puesto("Desarrollador")
                                .build(),
                        EmployeeResponseModel
                                .builder()
                                .id(1L)
                                .nombreCompleto("Nombre Empleado")
                                .apellidoPaterno("Apellido Paterno")
                                .sexo(Sexo.MASCULINO)
                                .fechaNacimiento("1990-01-01")
                                .puesto("Desarrollador")
                                .build(),
                        EmployeeResponseModel
                                .builder()
                                .id(1L)
                                .nombreCompleto("Nombre Empleado")
                                .apellidoPaterno("Apellido Paterno")
                                .sexo(Sexo.MASCULINO)
                                .fechaNacimiento("1990-01-01")
                                .puesto("Desarrollador")
                                .build()
                ));
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
