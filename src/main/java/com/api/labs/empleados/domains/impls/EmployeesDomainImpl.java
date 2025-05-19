package com.api.labs.empleados.domains.impls;

import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.dtos.EmployeeUpdateRequestModel;
import com.api.labs.empleados.mappers.EmployeeMapper;
import com.api.labs.empleados.persistence.entities.Employee;
import com.api.labs.empleados.services.EmployeeService;
import com.api.labs.empleados.utilities.DateTimes;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.api.labs.empleados.utilities.Employees.copyOf;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeesDomainImpl implements EmployeesDomain {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponseModel> createEmployees(List<EmployeeRequestModel> employees) {
        return employeeService
                .saveAll(employees
                        .stream()
                        .map(employeeMapper::toEmployee)
                        .toList())
                .stream()
                .map(employeeMapper::toEmployeeResponseModel)
                .toList();
    }

    @Override
    public Optional<EmployeeResponseModel> updateById(Long id, EmployeeUpdateRequestModel empleado) {
        return employeeService
                .findById(id)
                .map(copyOf(empleado))
                .map(employeeService::save)
                .map(employeeMapper::toEmployeeResponseModel);
    }

    @Override
    public List<EmployeeResponseModel> findAll() {
        return employeeService
                .findAll()
                .stream()
                .map(employeeMapper::toEmployeeResponseModel)
                .toList();
    }

    @Override
    public Optional<EmployeeResponseModel> findById(Long id) {
        return employeeService
                .findById(id)
                .map(employeeMapper::toEmployeeResponseModel);
    }

    @Override
    public void deleteById(Long id) {
        employeeService.deleteById(id);
    }
}
