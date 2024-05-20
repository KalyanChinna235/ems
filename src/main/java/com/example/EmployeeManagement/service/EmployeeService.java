package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long id,EmployeeDto updateEmployee);

    EmployeeDto deleteEmployee(Long id);
}
