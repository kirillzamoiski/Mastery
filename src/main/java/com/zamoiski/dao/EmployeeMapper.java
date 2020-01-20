package com.zamoiski.dao;

import com.zamoiski.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class EmployeeMapper implements RowMapper {
    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDepartmentId(rs.getInt("department_id"));
        employee.setJobTitle(rs.getString("job_title"));
        employee.setGender(rs.getString("gender"));
        employee.setDateOfBirth(rs.getDate("date_of_birth"));
        return employee;
    }
}
