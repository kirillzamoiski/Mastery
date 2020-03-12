package com.zamoiski.dao.jdbc;

import com.zamoiski.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int i) throws SQLException {
        Department department = new Department();
        department.setId(rs.getLong("id"));
        department.setDepartment_name(rs.getString("department_name"));
        department.setDateOfCreate(rs.getTimestamp("date_of_create").toLocalDateTime());
        return department;
    }
}
