package com.zamoiski.dao.jpa;

import com.zamoiski.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeJPAImpl extends CrudRepository<Employee,Long> {

}
