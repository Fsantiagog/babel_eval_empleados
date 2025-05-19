package com.api.labs.empleados.persistence.repositories;

import com.api.labs.empleados.persistence.entities.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
}
