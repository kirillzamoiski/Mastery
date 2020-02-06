package com.zamoiski;

import com.zamoiski.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department findById(Long theId);

    void save(Department department);

    void deleteById(Long theId);
}
