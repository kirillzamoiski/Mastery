package com.zamoiski.dao.jdbc;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.model.Department;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DepartmentDAOJdbcImpl implements DepartmentDAO {

    private static final String INSERT = "INSERT INTO public.department ( department_name,  date_of_create) " +
            " values(:department_name,:date_of_create)";
    private static final String SELECT_ALL = "SELECT * FROM public.department";
    private static final String FIND_BY_ID = "select * from public.department where id = :id";
    private static final String DELETE_BY_ID = "delete from public.department where id = :id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDAOJdbcImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }



    @Override
    public List<Department> findAll() {
        return namedParameterJdbcTemplate.query(SELECT_ALL, new DepartmentMapper());
    }

    @Override
    public Department findById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",Long.valueOf(theId));
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new DepartmentMapper());
    }

    @Override
    public void save(Department department) {
        Map namedParameters = new HashMap();
        namedParameters.put("department_name",department.getDepartment_name());
        namedParameters.put("date_of_create",department.getDateOfCreate());
        namedParameterJdbcTemplate.update(INSERT,namedParameters);
    }

    @Override
    public void deleteById(Long theId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id",Long.valueOf(theId));
        namedParameterJdbcTemplate.update(DELETE_BY_ID, namedParameters);
    }
}
