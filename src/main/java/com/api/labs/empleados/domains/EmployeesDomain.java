package com.api.labs.empleados.domains;

import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;

import java.util.List;
import java.util.Optional;

public interface EmployeesDomain {
    List<EmployeeResponseModel> createEmployeers(List<EmployeeRequestModel> employeers);

    Optional<EmployeeResponseModel> update(Long id, EmployeeRequestModel empleado);

    List<EmployeeResponseModel> findAll();

    Optional<EmployeeResponseModel> findById(Long id);

    Optional<EmployeeResponseModel> deleteById(Long id);
}
