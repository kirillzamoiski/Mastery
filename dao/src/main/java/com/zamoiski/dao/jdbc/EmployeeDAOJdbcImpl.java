package com.zamoiski.dao.jdbc;

import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.model.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class EmployeeDAOJdbcImpl implements EmployeeDAO {

    private static final String INSERT = "INSERT INTO employee(first_name, last_name, department_id, job_title, gender, date_of_birth) " +
            " values(:firstName,:lastName,:departmentId,:jobTitle,:gender,:dateOfBirth)";
    private static final String SELECT_ALL = "select * from employee";
    private static final String FIND_BY_ID = "select * from employee where employee_id = :id";
    private static final String DELETE_BY_ID = "delete * from employee where employee_id = :id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDAOJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
    }

    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query(SELECT_ALL, new EmployeeMapper());
    }

    @Override
    public Employee findById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("theId",Long.valueOf(theId));
        return (Employee) namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new EmployeeMapper());
    }

    @Override
    public void save(Employee employee) {
        Map namedParameters = new HashMap();
        namedParameters.put("firstName",employee.getFirstName());
        namedParameters.put("lastName",employee.getLastName());
        namedParameters.put("departmentId",employee.getDepartmentId());
        namedParameters.put("jobTitle",employee.getJobTitle());
        namedParameters.put("gender",employee.getGender());
        namedParameters.put("dateOfBirth",employee.getDateOfBirth());
        namedParameterJdbcTemplate.update(INSERT,namedParameters);

    }

    @Override
    public void deleteById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("theId",Long.valueOf(theId));
        namedParameterJdbcTemplate.update(DELETE_BY_ID, namedParameters);
    }
}
