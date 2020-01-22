package com.zamoiski.dao.jdbc;

import com.zamoiski.model.Department;
import com.zamoiski.model.Employee;
import com.zamoiski.model.JobTitle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setJobTitle(JobTitle.valueOf(rs.getString("job_name")));
        employee.setGender(rs.getString("gender"));
        employee.setDateOfBirth(rs.getTimestamp("date_of_birth").toLocalDateTime());
        return employee;
    }
}
