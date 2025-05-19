package com.api.labs.empleados.domains.impls;

import com.api.labs.empleados.domains.BitacoraDomain;
import com.api.labs.empleados.persistence.entities.Bitacora;
import com.api.labs.empleados.services.BitacoraService;
import com.api.labs.empleados.persistence.catalogs.Accion;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class BitacoraDomainImpl implements BitacoraDomain {

    private final BitacoraService bitacoraService;

    @Override
    public Bitacora create(HttpServletRequest request) {
        log.info("Creando Bitacora");
        return Optional
                .ofNullable(request)
                .map(buildBitacora())
                .map(bitacoraService::save)
                .orElse(null);
    }

    private Function<HttpServletRequest, Bitacora> buildBitacora() {
        return servletRequest -> Bitacora
                .builder()
                .accion(Accion.findByMethod(servletRequest.getMethod()))
                .entidad("empleados")
                .agent(servletRequest.getHeader("User-Agent"))
                .build();
    }
}
