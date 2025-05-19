package com.api.labs.empleados.domains.impls;

import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.dtos.EmployeeUpdateRequestModel;
import com.api.labs.empleados.mappers.EmployeeMapper;
import com.api.labs.empleados.services.EmployeeService;
import com.api.labs.empleados.persistence.catalogs.Sexo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static com.api.labs.empleados.utilities.EmployeesUtilTest.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeesDomainImplTest {

    @MockitoBean
    private EmployeeService employeeService;
    @MockitoBean
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeesDomain employeesDomain;


    @Test
    public void whenCreateEmployees_thenCreated(){
        //give
        List<EmployeeRequestModel> employees = buildSomeRequests();
        //when
        Mockito
                .when(employeeService.saveAll(Mockito.any()))
                .thenReturn(buildSomeEmployees());
        Mockito
                .when(employeeMapper.toEmployee(Mockito.any()))
                .thenReturn(buildEmployee());
        Mockito
                .when(employeeMapper.toEmployeeResponseModel(Mockito.any()))
                .thenReturn(buildResponse());

        List<EmployeeResponseModel> response = employeesDomain.createEmployees(employees);
        //then
        assertNotNull(response);
        assertEquals(4, response.size());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Nombre Empleado", response.get(0).getNombreCompleto());
        assertEquals("ApellidoPaterno", response.get(0).getApellidoPaterno());
        assertEquals(Sexo.MASCULINO, response.get(0).getSexo());
        assertEquals("01-01-2000", response.get(0).getFechaNacimiento());
        assertEquals("Desarrollador", response.get(0).getPuesto());

        //verify
        Mockito
                .verify(employeeService, Mockito.atLeastOnce())
                .saveAll(Mockito.any());
        Mockito
                .verify(employeeMapper, Mockito.atLeastOnce())
                .toEmployee(Mockito.any());
        Mockito
                .verify(employeeMapper, Mockito.atLeastOnce())
                .toEmployeeResponseModel(Mockito.any());
    }

    @Test
    public void whenUpdateEmployees_thenUpdated(){
        //give
        EmployeeUpdateRequestModel employeeUpdateRequestModel = EmployeeUpdateRequestModel
                .builder()
                .puesto("Nuevo Puesto")
                .build();
        Long idSavedEmployee = 1L;
        //when
        Mockito
                .when(employeeService.findById(idSavedEmployee))
                .thenReturn(Optional.of(buildEmployee()));
        Mockito
                .when(employeeService.save(Mockito.any()))
                .thenReturn(buildEmployee());
        Mockito
                .when(employeeMapper.toEmployeeResponseModel(Mockito.any()))
                .thenReturn(buildUpdatedResponse());

        Optional<EmployeeResponseModel> response = employeesDomain.updateById(idSavedEmployee, employeeUpdateRequestModel);

        assertNotNull(response);
        Assertions.assertAll(
                () -> assertTrue(response.isPresent()),
                () -> assertEquals(idSavedEmployee, response.map(EmployeeResponseModel::getId).orElseGet(() -> null)),
                () -> assertEquals("Nombre Empleado", response.map(EmployeeResponseModel::getNombreCompleto).orElseGet(() -> null)),
                () -> assertEquals("ApellidoPaterno", response.map(EmployeeResponseModel::getApellidoPaterno).orElseGet(() -> null)),
                () -> assertEquals(Sexo.MASCULINO, response.map(EmployeeResponseModel::getSexo).orElseGet(() -> null)),
                () -> assertEquals("01-01-2000", response.map(EmployeeResponseModel::getFechaNacimiento).orElseGet(() -> null)),
                () -> assertEquals("Nuevo Puesto", response.map(EmployeeResponseModel::getPuesto).orElseGet(() -> null))
        );
        //verify
        Mockito
                .verify(employeeService, Mockito.atLeastOnce())
                .findById(idSavedEmployee);
        Mockito
                .verify(employeeService, Mockito.atLeastOnce())
                .save(Mockito.any());
        Mockito
                .verify(employeeMapper, Mockito.atLeastOnce())
                .toEmployeeResponseModel(Mockito.any());
    }

    @Test
    public void wheFindAllEmployees_thenAllEmployees(){
        //give
        //when
        Mockito
                .when(employeeService.findAll())
                .thenReturn(buildSomeEmployees());

        Mockito
                .when(employeeMapper.toEmployeeResponseModel(Mockito.any()))
                .thenReturn(buildResponse());

        List<EmployeeResponseModel> response = employeesDomain.findAll();
        assertNotNull(response);
        assertEquals(4, response.size());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Nombre Empleado", response.get(0).getNombreCompleto());
        assertEquals("ApellidoPaterno", response.get(0).getApellidoPaterno());
        assertEquals(Sexo.MASCULINO, response.get(0).getSexo());
        assertEquals("01-01-2000", response.get(0).getFechaNacimiento());
        assertEquals("Desarrollador", response.get(0).getPuesto());

        //verify
        Mockito
                .verify(employeeService, Mockito.atLeastOnce())
                .findAll();

        Mockito
                .verify(employeeMapper, Mockito.times(4))
                .toEmployeeResponseModel(Mockito.any());
    }

    @Test
    public void wheFindById_thenFoundEmployee(){
        //give
        Long idSavedEmployee = 1L;
        //when
        Mockito
                .when(employeeService.findById(idSavedEmployee))
                .thenReturn(Optional.of(buildEmployee()));

        Mockito
                .when(employeeMapper.toEmployeeResponseModel(Mockito.any()))
                .thenReturn(buildResponse());
        Optional<EmployeeResponseModel> response = employeesDomain.findById(idSavedEmployee);
        assertNotNull(response);
        Assertions.assertAll(
                () -> assertTrue(response.isPresent()),
                () -> assertEquals(idSavedEmployee, response.map(EmployeeResponseModel::getId).orElseGet(() -> null)),
                () -> assertEquals("Nombre Empleado", response.map(EmployeeResponseModel::getNombreCompleto).orElseGet(() -> null)),
                () -> assertEquals("ApellidoPaterno", response.map(EmployeeResponseModel::getApellidoPaterno).orElseGet(() -> null)),
                () -> assertEquals(Sexo.MASCULINO, response.map(EmployeeResponseModel::getSexo).orElseGet(() -> null)),
                () -> assertEquals("01-01-2000", response.map(EmployeeResponseModel::getFechaNacimiento).orElseGet(() -> null)),
                () -> assertEquals("Desarrollador", response.map(EmployeeResponseModel::getPuesto).orElseGet(() -> null))
        );

        //verify
        Mockito
                .verify(employeeService, Mockito.atLeastOnce())
                .findById(idSavedEmployee);
        Mockito
                .verify(employeeMapper, Mockito.atLeastOnce())
                .toEmployeeResponseModel(Mockito.any());
    }

    @Test
    public void whenDeleteEmployees_thenDeleted(){
        //give
        Long idSavedEmployee = 1L;
        //when
        Mockito
                .doNothing()
                .when(employeeService)
                .deleteById(idSavedEmployee);
        employeesDomain.deleteById(idSavedEmployee);

        Mockito
                .verify(employeeService, Mockito.atLeastOnce())
                .deleteById(idSavedEmployee);
    }
}