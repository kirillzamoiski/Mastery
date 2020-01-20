package com.zamoiski.service;


import com.zamoiski.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable Long employeeId){
        return employeeService.findById(employeeId);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employeeService.save(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.save(employee);

        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteById(employeeId);
        return ResponseEntity.ok().build();
       // return "Employee with id "+employeeId+" was deleted";
    }
}
