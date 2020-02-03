package com.zamoiski.dao.jpa;

import com.zamoiski.model.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentJPAImpl extends CrudRepository<Department,Long> {
}
