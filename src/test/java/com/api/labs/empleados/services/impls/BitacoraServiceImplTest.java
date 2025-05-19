package com.api.labs.empleados.services.impls;

import com.api.labs.empleados.persistence.entities.Bitacora;
import com.api.labs.empleados.persistence.repositories.BitacoraRepository;
import com.api.labs.empleados.services.BitacoraService;
import com.api.labs.empleados.persistence.catalogs.Accion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static com.api.labs.empleados.utilities.BitacoraUtils.buildBitacora;
import static com.api.labs.empleados.utilities.BitacoraUtils.buildSomeBitacoras;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BitacoraServiceImplTest {


    @MockitoBean
    private BitacoraRepository bitacoraRepository;

    @Autowired
    private BitacoraService bitacoraService;

    @Test
    void whenFindAllBitacoras_thenReturnBitacoras() {
        //give
        //when
        Mockito
                .when(bitacoraRepository.findAll())
                .thenReturn(buildSomeBitacoras());

        List<Bitacora> response = bitacoraService.findAll();
        //then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        //verify
        Mockito
                .verify(bitacoraRepository, Mockito.times(1))
                .findAll();

    }

    @Test
    void whenFindBitacoraById_thenReturnBitacora() {
        //given
        Long savedBitacoraId = 1L;
        //when
        Mockito
                .when(bitacoraRepository.findById(savedBitacoraId))
                .thenReturn(Optional.of(buildBitacora()));

        Optional<Bitacora> response = bitacoraService.findById(savedBitacoraId);
        //then
        assertNotNull(response);
        assertTrue(response.isPresent());
        assertEquals(savedBitacoraId, response.get().getId());
        assertEquals(Accion.CREAR_EMPLEADO, response.map(Bitacora::getAccion).orElseGet(() -> null));
        assertEquals("agent", response.map(Bitacora::getAgent).orElseGet(() -> null));
        assertEquals("entidad", response.map(Bitacora::getEntidad).orElseGet(() -> null));
        assertNotNull(response.map(Bitacora::getFechaHora).orElseGet(() -> null));

        //verify
        Mockito
                .verify(bitacoraRepository, Mockito.times(1))
                .findById(savedBitacoraId);
    }

    @Test
    public void whenSaveBitacora_thenReturnBitacora() {
        //give
        Bitacora requestBitacora = buildBitacora();
        //when
        Mockito
                .when(bitacoraRepository.save(requestBitacora))
                .thenReturn(buildBitacora());

        Bitacora response = bitacoraService.save(requestBitacora);
        //then
        assertNotNull(response);
        assertEquals(requestBitacora.getId(), response.getId());

        //verify
        Mockito
                .verify(bitacoraRepository, Mockito.times(1))
                .save(Mockito.any(Bitacora.class));
    }

    @Test
    public void whenSaveAnBitacora_ThenThrowException() throws RuntimeException{
        //given
        Bitacora requiestBitacora = buildBitacora();
        //when
        Mockito
                .when(bitacoraRepository.save(requiestBitacora))
                .thenThrow(new RuntimeException());
        //then
        assertThrows(RuntimeException.class, () -> bitacoraService.save(requiestBitacora));
        //verify
        Mockito
                .verify(bitacoraRepository, Mockito.times(1))
                .save(Mockito.any(Bitacora.class));
    }

    @Test
    public void whenDeleteBitacoraById_thenBitacoraIsDeleted() {
        //given
        Long savedId = 1L;
        //when
        Mockito
                .when(bitacoraRepository.findById(savedId))
                .thenReturn(Optional.of(buildBitacora()));
        Mockito
                .doNothing()
                .when(bitacoraRepository)
                .deleteById(savedId);
        //then
        bitacoraService.deleteById(savedId);
        //verify
        Mockito
                .verify(bitacoraRepository, Mockito.times(1))
                .findById(savedId);
        Mockito
                .verify(bitacoraRepository, Mockito.times(1))
                .deleteById(savedId);
    }
    @Test
    public void whenDeleteBitacoraById_thenThrowEntityNotFoundException() throws RuntimeException {
        //given
        Long savedId = 1L;
        //when
        Mockito
                .when(bitacoraRepository.findById(savedId))
                .thenReturn(Optional.empty());
        Mockito
                .doNothing()
                .when(bitacoraRepository)
                .deleteById(savedId);
        //then
        assertThrows(RuntimeException.class, () -> bitacoraService.deleteById(savedId));
        //verify
        Mockito
                .verify(bitacoraRepository, Mockito.times(1))
                .findById(savedId);
        Mockito
                .verify(bitacoraRepository, Mockito.never())
                .deleteById(savedId);
    }
}