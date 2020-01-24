package com.zamoiski.config;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.dao.jdbc.DepartmentDAOJdbcImpl;
import com.zamoiski.dao.jdbc.EmployeeDAOJdbcImpl;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class TestConfiguration {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");
        return dataSourceBuilder.build();
    }

    @Bean
    public EmployeeDAO employeeDAO() {
        return new EmployeeDAOJdbcImpl(getDataSource());
    }

    @Bean
    public DepartmentDAO departmentDAO() { return new DepartmentDAOJdbcImpl(getDataSource()); }

    @Bean
    public JdbcTemplate jdbcTemplate(){return new JdbcTemplate(getDataSource());}
}
