package com.zamoiski.dao.jdbc;

import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class EmployeeDAOJdbcImpl implements EmployeeDAO {

    private static final String INSERT = "INSERT INTO public.employee( first_name, last_name, job_name, gender, date_of_birth, department_id) \n" +
        "VALUES (:first_name,:last_name,CAST(:job_name AS positions),:gender,:date_of_birth,:department_id)";
    private static final String SELECT_ALL = "select * from public.employee";
    private static final String FIND_BY_ID = "select * from public.employee where employee_id = :id";
    private static final String DELETE_BY_ID = "delete from public.employee where employee_id = :id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EmployeeDAOJdbcImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query(SELECT_ALL, new EmployeeMapper());
    }

    @Override
    public Employee findById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",Long.valueOf(theId));
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new EmployeeMapper());
    }

    @Override
    public void save(Employee employee) {
        Map namedParameters = new HashMap();
        namedParameters.put("first_name",employee.getFirstName());
        namedParameters.put("last_name",employee.getLastName());
        namedParameters.put("job_name",employee.getJobTitle().name());
        namedParameters.put("gender",employee.getGender());
        namedParameters.put("date_of_birth",employee.getDateOfBirth());
        namedParameters.put("department_id",employee.getDepartment().getId());
        namedParameterJdbcTemplate.update(INSERT,namedParameters);

    }

    @Override
    public void deleteById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",Long.valueOf(theId));
        namedParameterJdbcTemplate.update(DELETE_BY_ID, namedParameters);
    }
}
