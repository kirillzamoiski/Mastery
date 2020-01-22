package com.zamoiski.dao.jdbc;

import com.zamoiski.model.Department;
import com.zamoiski.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int i) throws SQLException {
        Department department = new Department();
        department.setName(rs.getString("name"));
        department.setDateOfCreate(rs.getTimestamp("date_of_create").toLocalDateTime());
        List<Employee> employees = new ArrayList<>();
        while (rs.next()){
            Employee employee = new EmployeeMapper().mapRow(rs,i);
            employee.setDepartment(department);
            employees.add(employee);
        }
        department.setEmployees(employees);
        return department;
    }
}
