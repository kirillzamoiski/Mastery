package com.zamoiski.mastery_java.service;

import com.zamoiski.mastery_java.dao.EmployeeDAO;
import com.zamoiski.mastery_java.entity.Employee;
import com.zamoiski.mastery_java.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO=employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(Long theId) {
        Employee employee=employeeDAO.findById(theId);

        if(employee==null){
            throw new NotFoundException("Employee is not found - "+ theId);
        }

        return employee;
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(Long theId) {
        if (employeeDAO.findById(theId)==null){
            throw new NotFoundException("Employee is not found - "+ theId);
        }
        employeeDAO.deleteById(theId);
    }
}
