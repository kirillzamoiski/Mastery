package com.zamoiski.mastery_java.rest;


import com.zamoiski.mastery_java.dao.EmployeeDAO;
import com.zamoiski.mastery_java.entity.Employee;
import com.zamoiski.mastery_java.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId){
        Employee employee=employeeService.findById(employeeId);

        if(employee==null){
            throw new RuntimeException("Employee is not found - "+ employeeId);
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);

        employeeService.save(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);

        return employee;
    }

    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee employee=employeeService.findById(employeeId);

        if(employee==null){
            throw new RuntimeException("Employee is not found - "+ employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Employee with id "+employeeId+" was deleted";
    }
}
