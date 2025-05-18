package com.api.labs.empleados.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
@RequiredArgsConstructor
public class BitacoraAspect {
    private final HttpServletRequest request;

    @Around("@annotation(com.api.labs.empleados.aop.AfterRegister)")
    public Object register(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

}
