package com.api.labs.empleados.services.impls;

import com.api.labs.empleados.persistence.entities.Employee;
import com.api.labs.empleados.persistence.repositories.EmployeeRepository;
import com.api.labs.empleados.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static com.api.labs.empleados.utilities.EmployeesUtilTest.buildEmployee;
import static com.api.labs.empleados.utilities.EmployeesUtilTest.buildSomeEmployees;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {

    @MockitoBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void whenFindAllEmployees_thenReturnEmployees() {
        //give
        //when
        Mockito
                .when(employeeRepository.findAll())
                .thenReturn(buildSomeEmployees());

        List<Employee> response = employeeService.findAll();
        //then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        //verify
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .findAll();

    }

    @Test
    void whenFindEmployeeById_thenReturnEmployee() {
        //given
        Long savedEmployedId = 1L;
        //when
        Mockito
                .when(employeeRepository.findById(savedEmployedId))
                .thenReturn(Optional.of(buildEmployee()));

        Optional<Employee> response = employeeService.findById(savedEmployedId);
        //then
        assertNotNull(response);
        assertTrue(response.isPresent());
        assertEquals(savedEmployedId, response.get().getId());
        //verify
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .findById(savedEmployedId);
    }

    @Test
    public void whenSaveEmployee_thenReturnEmployee() {
        //give
        Employee requestEmployee = buildEmployee();
        //when
        Mockito
                .when(employeeRepository.save(requestEmployee))
                .thenReturn(buildEmployee());

        Employee response = employeeService.save(requestEmployee);
        //then
        assertNotNull(response);
        assertEquals(requestEmployee.getId(), response.getId());

        //verify
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .save(Mockito.any(Employee.class));
    }

    @Test
    public void whenSaveAnEmployee_ThenThrowException() throws RuntimeException{
        //given
        Employee requestEmployee = buildEmployee();
        //when
        Mockito
                .when(employeeRepository.save(requestEmployee))
                .thenThrow(new RuntimeException());
        //then
        assertThrows(RuntimeException.class, () -> employeeService.save(requestEmployee));
        //verify
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .save(Mockito.any(Employee.class));
    }

    @Test
    public void whenSaveSomeEmployees_thenAreSaved(){
        //given
        List<Employee> requestSomeEmployees = buildSomeEmployees();
        //when
        Mockito
                .when(employeeRepository.saveAll(requestSomeEmployees))
                .thenReturn(buildSomeEmployees());

        List<Employee> response = employeeService.saveAll(requestSomeEmployees);
        assertNotNull(response);
        assertFalse(response.isEmpty());
        //verify
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .saveAll(Mockito.anyList());
    }

    @Test
    public void whenSaveSomeEmployees_thenThrowException() throws RuntimeException{
        //given
        List<Employee> requestSomeEmployees = buildSomeEmployees();
        //when
        Mockito
                .when(employeeRepository.saveAll(requestSomeEmployees))
                .thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> employeeService.saveAll(requestSomeEmployees));
        //verify
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .saveAll(Mockito.anyList());

    }

    @Test
    public void whenDeleteEmployeeById_thenEmployeeIsDeleted() {
        //given
        Long savedEmployeeId = 1L;
        //when
        Mockito
                .when(employeeRepository.findById(savedEmployeeId))
                .thenReturn(Optional.of(buildEmployee()));
        Mockito
                .doNothing()
                .when(employeeRepository)
                .deleteById(savedEmployeeId);
        //then
        employeeService.deleteById(savedEmployeeId);
        //verify
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .findById(savedEmployeeId);
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .deleteById(savedEmployeeId);
    }
    @Test
    public void whenDeleteEmployeeById_thenThrowEntityNotFoundException() throws RuntimeException {
        //given
        Long savedEmployeeId = 1L;
        //when
        Mockito
                .when(employeeRepository.findById(savedEmployeeId))
                .thenReturn(Optional.empty());
        Mockito
                .doNothing()
                .when(employeeRepository)
                .deleteById(savedEmployeeId);
        //then
        assertThrows(RuntimeException.class, () -> employeeService.deleteById(savedEmployeeId));
        //verify
        Mockito
                .verify(employeeRepository, Mockito.times(1))
                .findById(savedEmployeeId);
        Mockito
                .verify(employeeRepository, Mockito.never())
                .deleteById(savedEmployeeId);
    }
}