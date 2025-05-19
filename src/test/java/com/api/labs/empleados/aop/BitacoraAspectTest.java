package com.api.labs.empleados.aop;

import com.api.labs.empleados.controllers.EmployeesController;
import com.api.labs.empleados.utilities.EmployeesUtilTest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


@SpringBootTest
class BitacoraAspectTest {

    @Autowired
    private EmployeesController employeesController;

    @MockitoBean
    private BitacoraAspect bitacoraAspect;

    @Test
    public void whenRegisterCallsBitacoraDomain() throws Throwable{

        employeesController.create(EmployeesUtilTest.buildSomeRequests());

        Mockito
                .verify(bitacoraAspect, Mockito.times(1))
                .register(Mockito.any(ProceedingJoinPoint.class));

    }

}