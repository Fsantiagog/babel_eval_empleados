package com.api.labs.empleados.persistence.repositories;

import com.api.labs.empleados.persistence.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
