package com.api.labs.empleados.mappers;

import com.api.labs.empleados.dtos.EmployeeRequestModel;
import com.api.labs.empleados.dtos.EmployeeResponseModel;
import com.api.labs.empleados.persistence.entities.Employee;
import com.api.labs.empleados.utilities.DateTimes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = {DateTimes.class})
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "primerNombre", target = "primerNombre")
    @Mapping(source = "segundoNombre", target = "segundoNombre")
    @Mapping(source = "apellidoPaterno", target = "apellidoPaterno")
    @Mapping(source = "apellidoMaterno", target = "apellidoMaterno")
    @Mapping(source = "sexo", target = "sexo")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento", qualifiedByName = "toLocalDate")
    @Mapping(source = "fechaNacimiento", target = "edad", qualifiedByName = "calculateAge")
    @Mapping(source = "puesto", target = "puesto")
    Employee toEmployee(EmployeeRequestModel employeeRequestModel);

    @Mapping(target = "id", source = "id")
    @Mapping(source = ".", target = "nombreCompleto", qualifiedByName = "buildFullName")
    @Mapping(source = "apellidoPaterno", target = "apellidoPaterno")
    @Mapping(source = "apellidoMaterno", target = "apellidoMaterno")
    @Mapping(source = "sexo", target = "sexo")
    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento", qualifiedByName = "localDateToString")
    @Mapping(source = "puesto", target = "puesto")
    EmployeeResponseModel toEmployeeResponseModel(Employee employee);

    @Named("buildFullName")
    default String buildFullName(Employee employee) {
        return String.format("%s %s", employee.getPrimerNombre(), Optional.ofNullable(employee.getSegundoNombre()).orElseGet(() -> "")).trim();
    }
}
