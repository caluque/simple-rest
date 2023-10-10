package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DataDto;
import com.example.dto.EmployeeDto;
import com.example.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "employees")
public class EmployeeController {
    
    private final EmployeeService employeeService;

    @GetMapping(value = "count")
    public DataDto<Long> getTotalEmployees() {
        long count = employeeService.getTotalEmployees();
        return new DataDto<>(count);
    }

    @GetMapping
    public DataDto<List<EmployeeDto>> getListEmployees(@RequestParam("numPage") int numPage,
                                                       @RequestParam("pageSize") int pageSize) {
        List<EmployeeDto> employeeDtos = employeeService.getListEmployees(numPage, pageSize);
        return new DataDto<>(employeeDtos);
    }

    @PostMapping
    public DataDto<String> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.saveEmployee(employeeDto);
        return new DataDto<>(null);
    }

}
