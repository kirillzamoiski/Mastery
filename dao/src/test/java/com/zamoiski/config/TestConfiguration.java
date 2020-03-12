package com.zamoiski.config;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.dao.jdbc.DepartmentDAOJdbcImpl;
import com.zamoiski.dao.jdbc.EmployeeDAOJdbcImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("employee-sql.properties"),
        @PropertySource("insert-value.sql")
})
public class TestConfiguration {

    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
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
