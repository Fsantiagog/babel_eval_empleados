package com.api.labs.empleados.controllers;

import com.api.labs.empleados.dtos.response.ArgumentHandlerError;
import com.api.labs.empleados.dtos.response.BadResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;

//DRY

@Log4j2
@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice(basePackages = "com.api.labs.empleados.controllers")
public class ControllerAdvisor extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.info("Handling handleConstraintViolationException");
        return ResponseEntity.badRequest().body(
                BadResponse
                        .builder()
                        .message("Bad Request: Validación de campos")
                        .success(false)
                        .timestamp(LocalDateTime.now().toString())
                        .method(Optional.of(((ServletWebRequest)request).getHttpMethod()).map(HttpMethod::name).orElseGet(() -> "METHOD_NOT_FOUND"))
                        .uri(Optional.of(((ServletWebRequest)request).getRequest()).map(HttpServletRequest::getServletPath).orElseGet(() -> "URL_NOT_FOUND"))
                        .errors(
                                ex.getConstraintViolations().stream()
                                        .map(error -> ArgumentHandlerError
                                                .builder()
                                                .field(error.getPropertyPath().toString())
                                                .message(error.getMessage())
                                                .rejectedValue(Optional.ofNullable(error.getInvalidValue()).map(Object::toString).orElseGet(() -> "REJECTED_VALUE_NOT_FOUND"))
                                                .build()
                                        )
                                        .toList()
                        )
                        .build()
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.info("Handling MethodArgumentNotValidException");
        return ResponseEntity.badRequest().body(
                BadResponse
                        .builder()
                        .message("Bad Request: Validación de campos")
                        .success(false)
                        .timestamp(LocalDateTime.now().toString())
                        .method(Optional.of(((ServletWebRequest)request).getHttpMethod()).map(HttpMethod::name).orElse("METHOD_NOT_FOUND"))
                        .uri(Optional.of(((ServletWebRequest)request).getRequest()).map(HttpServletRequest::getServletPath).orElse("URL_NOT_FOUND"))
                        .errors(
                                ex.getBindingResult().getFieldErrors().stream()
                                        .map(error -> ArgumentHandlerError
                                                .builder()
                                                .field(error.getField())
                                                .message(error.getDefaultMessage())
                                                .rejectedValue(Optional.ofNullable(error.getRejectedValue()).map(Object::toString).orElse("REJECTED_VALUE_NOT_FOUND"))
                                                .build()
                                        )
                                        .toList()
                        )
                        .build()
        );
    }
}
