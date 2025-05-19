package com.api.labs.empleados.domains;

import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.dtos.EmployeeUpdateRequestModel;

import java.util.List;
import java.util.Optional;

public interface EmployeesDomain {
    List<EmployeeResponseModel> createEmployees(List<EmployeeRequestModel> employees);

    Optional<EmployeeResponseModel> updateById(Long id, EmployeeUpdateRequestModel empleado);

    List<EmployeeResponseModel> findAll();

    Optional<EmployeeResponseModel> findById(Long id);

    void deleteById(Long id);
}
