package com.chikara.employeemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.chikara.employeemanager.entity.Employee;
import com.chikara.employeemanager.service.EmployeeService;
import com.chikara.employeemanager.util.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> listStudents() {     
        List<Employee>  employees = employeeService.getEmployees();
        return ApiResponse.send(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        return ApiResponse.send(employee);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.createEmployee(employee);
        return ApiResponse.send(newEmployee);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ApiResponse.send(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> deleteEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        employeeService.deleteEmployee(id);
        return ApiResponse.send(employee, "Employee deleted succesfully");
    }
}
