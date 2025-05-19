package com.api.labs.empleados.services;

import com.api.labs.empleados.persistence.entities.Bitacora;

import java.util.List;
import java.util.Optional;

public interface BitacoraService {
    Bitacora save(Bitacora bitacora);
    Optional<Bitacora> findById(Long id);
    List<Bitacora> findAll();
    void deleteById(Long id);
}
