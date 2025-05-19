package com.api.labs.empleados.services.impls;

import com.api.labs.empleados.persistence.entities.Bitacora;
import com.api.labs.empleados.persistence.repositories.BitacoraRepository;
import com.api.labs.empleados.services.BitacoraService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BitacoraServiceImpl implements BitacoraService {

    private final BitacoraRepository bitacoraRepository;

    @Transactional
    @Override
    public Bitacora save(Bitacora bitacora) {
        try {
            return bitacoraRepository.save(bitacora);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Bitacora> findById(Long id) {
        return bitacoraRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bitacora> findAll() {
        return bitacoraRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        try {
            bitacoraRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Bitacora no encontrado"));
            bitacoraRepository.deleteById(id);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new EntityNotFoundException("Bitacora no encontrado");
        }
    }
}
