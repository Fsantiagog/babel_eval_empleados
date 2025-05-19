package com.api.labs.empleados.services.impls;

import com.api.labs.empleados.persistence.entities.Employee;
import com.api.labs.empleados.persistence.repositories.EmployeeRepository;
import com.api.labs.empleados.services.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public Employee save(Employee employee) {
        log.info("Guardando el empleado");
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            log.error("Error saving employee: {}", e.getMessage());
            throw new RuntimeException("Error saving employee", e);
        }
    }

    @Transactional
    @Override
    public List<Employee> saveAll(List<Employee> employee) {
        log.info("Guardando los empleados");
        try {
            return employeeRepository.saveAll(employee);
        } catch (Exception e) {
            log.error("Error saving employees: {}", e.getMessage());
            throw new RuntimeException("Error saving employees", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Employee> findById(Long id) {
        log.info("buscando por id el empleado");
        return employeeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAll() {
        log.info("buscando todos los empleados");
        return employeeRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        log.info("eliminando por id el empleado");
        try {
            employeeRepository
                    .findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error deleting employee with id: " + id));
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting employee: {}", e.getMessage());
            throw new RuntimeException("Error deleting employee", e);
        }
    }
}
