package com.api.labs.empleados.utilities;

import lombok.experimental.UtilityClass;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@UtilityClass
public class DateTimes {

    @Named("toLocalDateTime")
    public static LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }

    @Named("calculateAge")
    public static Integer calculateAge(String fechaNacimiento) {
        LocalDate birthDate = toLocalDateTime(fechaNacimiento).toLocalDate();
        LocalDate now = LocalDateTime.now().toLocalDate();
        return Period.between(birthDate, now).getYears();
    }

    @Named("toString")
    public static String toString(LocalDateTime date) {
        return date.toString();
    }

}
