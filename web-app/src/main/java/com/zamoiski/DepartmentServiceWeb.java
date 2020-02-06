package com.zamoiski;

import com.zamoiski.model.Department;

import java.util.List;

public interface DepartmentServiceWeb {
    List<Department> findAll();

    Department findById(Long theId);

    void save(Department department);

    void update(Long theId, Department department);

    void deleteById(Long theId);
}
