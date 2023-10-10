package com.example.dto;

import java.time.LocalDate;
import java.time.Period;

public record EmployeeDto(long id, String name, LocalDate birthdate, int age) {

    public EmployeeDto(long id, String name, LocalDate birthdate){
        this(id, name, birthdate, 0);
    }

    public int age() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthdate, currentDate);
        return period.getYears();
    }

}
