package com.api.labs.empleados.components;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

@Log4j2
@Component
public class RequestHandlerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            Enumeration<String> headerNames = httpRequest.getHeaderNames();
            log.info("Request Headers:");
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = httpRequest.getHeader(headerName);
                log.info("{} : {}", headerName, headerValue);
            }
        }

        chain.doFilter(request, response);
    }

}
