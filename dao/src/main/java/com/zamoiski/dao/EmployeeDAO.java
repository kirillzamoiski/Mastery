package com.zamoiski.dao;

import com.zamoiski.model.Employee;

import java.util.List;

public interface EmployeeDAO {

     List<Employee> findAll();

     Employee findById(Long theId);

     void save(Employee employee);

     void deleteById(Long theId);
}
