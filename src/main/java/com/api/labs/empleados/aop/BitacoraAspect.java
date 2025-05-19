package com.api.labs.empleados.aop;

import com.api.labs.empleados.domains.BitacoraDomain;
import com.api.labs.empleados.dtos.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Aspect
@Component
@RequiredArgsConstructor
public class BitacoraAspect {
    private final HttpServletRequest request;
    private final BitacoraDomain bitacoraDomain;

    @Around("@annotation(com.api.labs.empleados.aop.AfterRegister)")
    public Object register(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returned = joinPoint.proceed();
        log.info("Bitacora proceed: {}", returned.getClass().getSimpleName());
        Optional
                .of(returned)
                .filter(ResponseEntity.class::isInstance)
                .map(ResponseEntity.class::cast)
                .map(ResponseEntity::getBody)
                .filter(Response.class::isInstance)
                .map(Response.class::cast)
                .filter(Response::getSuccess)
                .ifPresent(response -> bitacoraDomain.create(request));
        return returned;
    }

}
