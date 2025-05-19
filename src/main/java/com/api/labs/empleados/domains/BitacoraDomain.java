package com.api.labs.empleados.domains;

import com.api.labs.empleados.persistence.entities.Bitacora;
import jakarta.servlet.http.HttpServletRequest;

public interface BitacoraDomain {
    Bitacora create(HttpServletRequest request);
}
