package com.zamoiski;

import com.zamoiski.model.Department;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceWebImpl implements DepartmentServiceWeb {

    private static final String GET_DETARTMENTS_URL = "http://localhost:8080/api/departments";
    private static final String GET_DETARTMENT_URL = "http://localhost:8080/api/departments/{id}";
    private static final String CREATE_DETARTMENT_URL = "http://localhost:8080/api/departments";
    private static final String UPDATE_DETARTMENT_URL = "http://localhost:8080/api/departments/{id}";
    private static final String DELETE_DETARTMENT_URL = "http://localhost:8080/api/departments/{id}";

    private RestTemplate restTemplate;

    public DepartmentServiceWebImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Department> findAll() {
        return Arrays.asList(restTemplate.getForObject(GET_DETARTMENTS_URL, Department[].class));
    }

    @Override
    public Department findById(Long theId) {
        Map< String, Long > params = new HashMap<>();
        params.put("id", theId);
        return restTemplate.getForObject(GET_DETARTMENT_URL,Department.class,params);
    }

    @Override
    public void save(Department department) {
        restTemplate.postForObject(CREATE_DETARTMENT_URL, department, Department.class);
    }

    @Override
    public void update(Long theId, Department department) {
        Map < String, Long > params = new HashMap <> ();
        params.put("id", theId);
        restTemplate.put(UPDATE_DETARTMENT_URL,department,params);
    }

    @Override
    public void deleteById(Long theId) {
        Map< String, Long > params = new HashMap<>();
        params.put("id", theId);
        restTemplate.delete(DELETE_DETARTMENT_URL, params);
    }
}
