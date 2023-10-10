package com.example.repositories.h2conn;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.h2conn.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
