package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.dto.EmployeeDto;
import com.example.EmployeeManagement.exception.ResourceNotFoundException;
import com.example.EmployeeManagement.model.Employee;
import com.example.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {

        EmployeeDto saved = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {

        EmployeeDto getById = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(getById, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> allEmps = employeeService.getAllEmployees();
        return new ResponseEntity<>(allEmps, HttpStatus.OK);
    }

    @PutMapping("{eid}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("eid") Long id, @RequestBody EmployeeDto updateEmployee) {

        EmployeeDto updateEmpl = employeeService.updateEmployee(id, updateEmployee);

        return new ResponseEntity<>(updateEmpl, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Long id) {
        EmployeeDto emp = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }
}
