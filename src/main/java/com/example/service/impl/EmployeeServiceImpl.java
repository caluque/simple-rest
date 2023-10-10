package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dto.EmployeeDto;
import com.example.entities.h2conn.Employee;
import com.example.repositories.h2conn.EmployeeRepository;
import com.example.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getListEmployees(int numPage, int pageSize) {
        Pageable pageable = PageRequest.of(numPage-1, pageSize);

        return employeeRepository.findAll(pageable).stream()
            .map(e -> new EmployeeDto(e.getId(), e.getName(), e.getBirthdate()))
            .collect(Collectors.toList());
    }

    @Override
    public Long getTotalEmployees() {
        return employeeRepository.count();
    }

    @Override
    public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(null, employeeDto.name(), employeeDto.birthdate());
        employeeRepository.save(employee);
    }

}
