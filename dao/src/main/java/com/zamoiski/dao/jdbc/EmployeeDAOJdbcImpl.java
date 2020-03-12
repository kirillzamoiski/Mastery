package com.zamoiski.dao.jdbc;

import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.model.Department;
import com.zamoiski.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:employee-sql.properties")
public class EmployeeDAOJdbcImpl implements EmployeeDAO {

    @Value("${Insert}")
    private String insert;
    @Value("${update}")
    private String update;
    @Value("${selectAll}")
    private String selectAll;
    @Value("${findById}")
    private String findById;
    @Value("${deleteById}")
    private String deleteById;
    @Value("${findIdByName}")
    private String findIdByName;
    @Value("${updateTitleById}")
    private String updateTitleById;


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public EmployeeDAOJdbcImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query(selectAll, new EmployeeMapper());
    }

    @Override
    public Employee findById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",Long.valueOf(theId));
        return namedParameterJdbcTemplate.queryForObject(findById, namedParameters, new EmployeeMapper());
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
        if(employee.getId()==null){
            namedParameterJdbcTemplate.update(insert,namedParameters);
        }
        else {
            namedParameters.put("employee_id",employee.getId());
            namedParameterJdbcTemplate.update(update,namedParameters);
        }
    }

    @Override
    public void deleteById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",theId);
        namedParameterJdbcTemplate.update(deleteById, namedParameters);
    }

    @Override
    public void updateTitle(String title, String departmentName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("departmentName",departmentName);
        Department department = namedParameterJdbcTemplate.queryForObject(findIdByName,namedParameters,new DepartmentMapper());
        Map namedParameters1 = new HashMap();
        namedParameters1.put("title",title);
        namedParameters1.put("id",department.getId());
        namedParameterJdbcTemplate.update(updateTitleById,namedParameters1);
    }
}
