package com.zamoiski.dao.jdbc;

import com.zamoiski.config.TestConfiguration;
import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.model.Department;
import com.zamoiski.model.Employee;
import com.zamoiski.model.JobTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "classpath:insert-value.sql")
class EmployeeDAOJdbcImplTest {

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() {
        List<Employee> employees = employeeDAO.findAll();
        assertEquals(2,employees.size());
    }

    @Test
    void findById() {
        List<Employee> employees = employeeDAO.findAll();
        Employee employee = employeeDAO.findById(employees.get(1).getId());
        assertEquals("Alice",employee.getFirstName());
        assertEquals("Ivanova",employee.getLastName());
    }

    @Test
    void save() {
        Department department = new Department(2L,"DTP",LocalDateTime.now());
        departmentDAO.save(department);
        Employee employee = new Employee("Petya","Horris",JobTitle.TEAM_LEAD, "MALE",LocalDateTime.now(),department);
        employeeDAO.save(employee);
        List<Employee> employees = employeeDAO.findAll();
        assertEquals(3,employees.size());
    }

    @Test
    void deleteById() {
        List<Employee> employees = employeeDAO.findAll();
        employeeDAO.deleteById(employees.get(1).getId());
        employees = employeeDAO.findAll();
        assertEquals(1,employees.size());
    }

}