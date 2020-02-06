package com.zamoiski;

import com.zamoiski.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceWebImpl implements EmployeeServiceWeb{

    private static final String GET_EMPLOYEES_URL = "http://localhost:8080/api/employees";
    private static final String GET_EMPLOYEE_URL = "http://localhost:8080/api/employees/{id}";
    private static final String CREATE_EMPLOYEE_URL = "http://localhost:8080/api/employees";
    private static final String UPDATE_EMPLOYEE_URL = "http://localhost:8080/api/employees";
    private static final String DELETE_EMPLOYEE_URL = "http://localhost:8080/api/employees/{id}";

    private RestTemplate restTemplate;

    public EmployeeServiceWebImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Employee> findAll() {
        return Arrays.asList(restTemplate.getForObject(GET_EMPLOYEES_URL,Employee[].class));
    }

    @Override
    public Employee findById(Long theId) {
        Map < String, Long > params = new HashMap <> ();
        params.put("id", theId);
        return restTemplate.getForObject(GET_EMPLOYEE_URL,Employee.class,params);
    }

    @Override
    public void save(Employee employee) {
        restTemplate.postForObject(CREATE_EMPLOYEE_URL, employee, Employee.class);
    }

    @Override
    public void update(Long theId ,Employee employee) {
        Map < String, Long > params = new HashMap <> ();
        params.put("id", theId);
        restTemplate.put(UPDATE_EMPLOYEE_URL,employee);
    }

    @Override
    public void deleteById(Long theId) {
        Map< String, Long > params = new HashMap<>();
        params.put("id", theId);
        restTemplate.delete(DELETE_EMPLOYEE_URL, params);
    }
}
