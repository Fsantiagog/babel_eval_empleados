package com.api.labs.empleados.utilities;

import lombok.experimental.UtilityClass;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimes {
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    @Named("toLocalDateTime")
    public static LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }

    @Named("toLocalDate")
    public static LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    @Named("calculateAge")
    public static Integer calculateAge(String fechaNacimiento) {
        LocalDate birthDate = toLocalDate(fechaNacimiento);
        LocalDate now = LocalDateTime.now().toLocalDate();
        return Period.between(birthDate, now).getYears();
    }

    @Named("localDateToString")
    public static String localDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}
