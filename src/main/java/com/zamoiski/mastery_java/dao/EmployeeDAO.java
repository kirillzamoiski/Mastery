package com.zamoiski.mastery_java.dao;

import com.zamoiski.mastery_java.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> findAll();

    public Employee findById(long theId);

    public void save(Employee employee);

    public void deleteById(long theId);
}