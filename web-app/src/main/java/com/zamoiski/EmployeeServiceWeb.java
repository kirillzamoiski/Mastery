package com.zamoiski;

import com.zamoiski.model.Employee;

import java.util.List;

public interface EmployeeServiceWeb {


    List<Employee> findAll();

    Employee findById(Long theId);

    void save(Employee employee);

    void update(Long theId, Employee employee);

    void deleteById(Long theId);
}
