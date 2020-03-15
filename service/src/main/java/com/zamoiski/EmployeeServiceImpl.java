package com.zamoiski;

import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.error.NotFoundException;
import com.zamoiski.model.DNameTitle;
import com.zamoiski.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, JmsTemplate jmsTemplate){
        this.employeeDAO=employeeDAO;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(Long theId) {
        Employee employee=employeeDAO.findById(theId);

        if(employee==null){
            throw new NotFoundException("Employee is not found - "+ theId);
        }

        return employee;
    }

    @Override
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public void deleteById(Long theId) {
        if (employeeDAO.findById(theId)==null){
            throw new NotFoundException("Employee is not found - "+ theId);
        }
        employeeDAO.deleteById(theId);
    }

    @Override
    public void updateTitle(String title, String departmentName) {
        jmsTemplate.convertAndSend("changeTitle",new DNameTitle(title,departmentName));
    }
}
