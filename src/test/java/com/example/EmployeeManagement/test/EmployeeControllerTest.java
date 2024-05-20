package com.example.EmployeeManagement.test;

import com.example.EmployeeManagement.controller.EmployeeController;
import com.example.EmployeeManagement.dto.EmployeeDto;
import com.example.EmployeeManagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addEmployeeTest() {

        EmployeeDto employeeDto = new EmployeeDto();

        when(employeeService.createEmployee(any(EmployeeDto.class))).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> responseEntity = employeeController.addEmployee(employeeDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(employeeDto, responseEntity.getBody());
        verify(employeeService, times(1)).createEmployee(employeeDto);
    }

    @Test
    public void getEmployeeByIdTest() {

        Long empId = 1L;
        EmployeeDto employeeDto = new EmployeeDto();

        when(employeeService.getEmployeeById(empId)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> responseEntity = employeeController.getById(empId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employeeDto, responseEntity.getBody());
        verify(employeeService, times(1)).getEmployeeById(empId);
    }

    @Test
    public void getAllEmployeesTest() {

        List<EmployeeDto> employees = Arrays.asList(new EmployeeDto(), new EmployeeDto());

        when(employeeService.getAllEmployees()).thenReturn(employees);
        ResponseEntity<List<EmployeeDto>> responseEntity = employeeController.getAllEmployees();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employees, responseEntity.getBody());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void updateEmployeeTest() {
        Long empId = 1L;
    EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("kalyan");

        when(employeeService.updateEmployee(empId, employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> responseEntity = employeeController.updateEmployee(empId, employeeDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employeeDto, responseEntity.getBody());
        verify(employeeService, times(1)).updateEmployee(empId, employeeDto);
    }
}
