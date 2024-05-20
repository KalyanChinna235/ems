package com.example.EmployeeManagement.serviceImpl;

import com.example.EmployeeManagement.dto.EmployeeDto;
import com.example.EmployeeManagement.exception.ResourceNotFoundException;
import com.example.EmployeeManagement.mapper.EmployeeMapper;
import com.example.EmployeeManagement.model.Employee;
import com.example.EmployeeManagement.repository.EmployeeRepository;
import com.example.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Map EmployeeDto to Employee entity
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        // Save the Employee entity to the database
        Employee savedEmployee = employeeRepository.save(employee);

        // Map the saved Employee entity back to EmployeeDto and return
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        /*Employee employeeId = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not found with this Id: " + id));

        return EmployeeMapper.mapToEmployeeDto(employeeId);*/
        return employeeRepository.findById(id)
                .map(EmployeeMapper::mapToEmployeeDto)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not found with this Id: " + id));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> allEmps = employeeRepository.findAll();
        return allEmps.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto updateEmployee) {
        Employee upEmp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not found with this id: " + id));
        upEmp.setFirstName(updateEmployee.getFirstName());
        upEmp.setLastName(updateEmployee.getLastName());
        upEmp.setEmail(updateEmployee.getEmail());

        Employee update = employeeRepository.save(upEmp);
        return EmployeeMapper.mapToEmployeeDto(update);
    }

    @Override
    public EmployeeDto deleteEmployee(Long id) {

       Employee empDel= employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee id is not found with is id: "+id));
        employeeRepository.deleteById(empDel.getId());

        return null;
    }
}


