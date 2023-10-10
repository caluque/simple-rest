package com.example.service;

import java.util.List;

import com.example.dto.EmployeeDto;

public interface EmployeeService {

    List<EmployeeDto> getListEmployees(int numPage, int pageSize);
    Long getTotalEmployees();
    void saveEmployee(EmployeeDto employeeDto);

}
