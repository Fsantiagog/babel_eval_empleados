package com.api.labs.empleados.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DateTimesTest {

    @Test
    public void whenToLocalDateTime() {
        //given
        String date = LocalDateTime.now().toString();
        //when
        LocalDateTime result = DateTimes.toLocalDateTime(date);
        assertNotNull(result);
    }

    @Test
    public void whenToLocalDate() {
        String date = "01-01-2020";
        LocalDate result = DateTimes.toLocalDate(date);
        assertNotNull(result);
    }

    @Test
    public void whenCalculateAge(){
        //give
        String birthday = "01-01-2020";
        Integer age = DateTimes.calculateAge(birthday);
        assertNotNull(age);
    }

    @Test
    public void whenLocalDateToString(){
        LocalDate localDate = LocalDate.now();
        String date = DateTimes.localDateToString(localDate);
        assertNotNull(date);
    }
}