package com.chikara.employeemanager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chikara.employeemanager.entity.Employee;
import com.chikara.employeemanager.exception.UserNotFoundException;
import com.chikara.employeemanager.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Employee by id "+id+" not found"));
    }

    public Employee createEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId, Employee employee) {
        Employee _employee = getEmployee(employeeId);
        _employee.setName(employee.getName());
        _employee.setJobTitle(employee.getJobTitle());
        _employee.setPhone(employee.getPhone());
        _employee.setEmail(employee.getEmail());
        _employee.setImageUrl(employee.getImageUrl());
        return employeeRepository.save(_employee);
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

}
