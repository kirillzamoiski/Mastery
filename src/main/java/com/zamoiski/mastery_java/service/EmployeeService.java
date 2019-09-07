package com.zamoiski.mastery_java.service;

import com.zamoiski.mastery_java.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(long theId);

    public void save(Employee employee);

    public void deleteById(long theId);
}