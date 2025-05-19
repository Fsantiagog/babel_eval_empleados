package com.api.labs.empleados.domains.impls;

import com.api.labs.empleados.domains.BitacoraDomain;
import com.api.labs.empleados.persistence.entities.Bitacora;
import com.api.labs.empleados.services.BitacoraService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static com.api.labs.empleados.utilities.BitacoraUtils.buildBitacora;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BitacoraDomainImplTest {

    @MockitoBean
    private BitacoraService bitacoraService;

    @Autowired
    private BitacoraDomain bitacoraDomain;

    @Test
    public void whenCreateBitacora(){
        //given
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito
                .when(bitacoraService.save(Mockito.any()))
                .thenReturn(buildBitacora());

        Bitacora bitacora = bitacoraDomain.create(request);

        assertNotNull(bitacora);

        Mockito.verify(bitacoraService, Mockito.times(1)).save(Mockito.any());
    }
}