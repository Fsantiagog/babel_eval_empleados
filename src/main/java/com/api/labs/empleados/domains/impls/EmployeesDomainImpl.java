package com.api.labs.empleados.domains.impls;

import com.api.labs.empleados.domains.EmployeesDomain;
import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.mappers.EmployeeMapper;
import com.api.labs.empleados.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeesDomainImpl implements EmployeesDomain {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponseModel> createEmployeers(List<EmployeeRequestModel> employeers) {
        return employeeService
                .saveAll(employeers
                        .stream()
                        .map(employeeMapper::toEmployee)
                        .toList())
                .stream()
                .map(employeeMapper::toEmployeeResponseModel)
                .toList();
    }

    @Override
    public Optional<EmployeeResponseModel> update(Long id, EmployeeRequestModel empleado) {
        return employeeService
                .findById(id)
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
    public Optional<EmployeeResponseModel> deleteById(Long id) {
        return employeeService
                .findById(id)
                .map(employee -> {
                    employeeService.deleteById(id);
                    return employeeMapper.toEmployeeResponseModel(employee);
                });
    }
}
