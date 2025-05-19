package com.api.labs.empleados.services;

import com.api.labs.empleados.persistence.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee save(Employee employee);
    List<Employee> saveAll(List<Employee> employee);
    Optional<Employee> findById(Long id);
    List<Employee> findAll();
    void deleteById(Long id);

}
