package com.api.labs.empleados.utilities;

import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeUpdateRequestModel;
import com.api.labs.empleados.persistence.entities.Employee;
import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.function.Function;

@UtilityClass
public class Employees {


    public static Function<Employee, Employee> copyOf(EmployeeUpdateRequestModel empleado) {
        return foundEmployee -> {
            foundEmployee.setEdad(Optional.ofNullable(empleado.getEdad()).orElseGet(foundEmployee::getEdad));
            foundEmployee.setPrimerNombre(Optional.ofNullable(empleado.getPrimerNombre()).orElseGet(foundEmployee::getPrimerNombre));
            foundEmployee.setSegundoNombre(Optional.ofNullable(empleado.getSegundoNombre()).orElseGet(foundEmployee::getSegundoNombre));
            foundEmployee.setApellidoPaterno(Optional.ofNullable(empleado.getApellidoPaterno()).orElseGet(foundEmployee::getApellidoPaterno));
            foundEmployee.setApellidoMaterno(Optional.ofNullable(empleado.getApellidoMaterno()).orElseGet(foundEmployee::getApellidoMaterno));
            foundEmployee.setSexo(Optional.ofNullable(empleado.getSexo()).orElseGet(foundEmployee::getSexo));
            foundEmployee.setFechaNacimiento(Optional.ofNullable(empleado.getFechaNacimiento()).map(DateTimes::toLocalDate).orElseGet(foundEmployee::getFechaNacimiento));
            foundEmployee.setPuesto(Optional.ofNullable(empleado.getPuesto()).orElseGet(foundEmployee::getPuesto));
            return foundEmployee;
        };
    }

}
