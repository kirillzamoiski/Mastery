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

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
class EmployeeDAOJdbcImplTest {

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE employee RESTART IDENTITY CASCADE;");
        jdbcTemplate.execute("TRUNCATE TABLE department RESTART IDENTITY CASCADE;");
    }

    @Test
    void findAll() {
        Department departmentOne = new Department(1L,"DTP",LocalDateTime.now());
        departmentDAO.save(departmentOne);
        Employee employee1 = new Employee("Alex","Ivanov",JobTitle.TESTER, "MALE",LocalDateTime.now(),departmentOne);
        Employee employee2 = new Employee("Alice","Ivanova",JobTitle.HR, "FEMALE",LocalDateTime.now(),departmentOne);
        employeeDAO.save(employee1);
        employeeDAO.save(employee2);
        List<Employee> employees = employeeDAO.findAll();
        assertEquals(2,employees.size());
    }

    @Test
    void findById() {
        Department departmentOne = new Department(1L,"DTP",LocalDateTime.now());
        departmentDAO.save(departmentOne);
        Employee employee1 = new Employee("Alex","Ivanov",JobTitle.TESTER, "MALE",LocalDateTime.now(),departmentOne);
        Employee employee2 = new Employee("Alice","Ivanova",JobTitle.HR, "FEMALE",LocalDateTime.now(),departmentOne);
        employeeDAO.save(employee1);
        employeeDAO.save(employee2);
        List<Employee> employees = employeeDAO.findAll();
        Employee employee = employeeDAO.findById(employees.get(1).getId());
        assertEquals("Alice",employee.getFirstName());
        assertEquals("Ivanova",employee.getLastName());
    }

    @Test
    void save() {
        Department departmentOne = new Department(1L,"DTP",LocalDateTime.now());
        departmentDAO.save(departmentOne);
        Employee employee = new Employee("Alex","Ivanov",JobTitle.TESTER, "MALE",LocalDateTime.now(),departmentOne);
        employeeDAO.save(employee);
        List<Employee> employees = employeeDAO.findAll();
        assertEquals(1,employees.size());
    }

    @Test
    void deleteById() {
        Department departmentOne = new Department(1L,"DTP",LocalDateTime.now());
        departmentDAO.save(departmentOne);
        Employee employee1 = new Employee("Alex","Ivanov",JobTitle.TESTER, "MALE",LocalDateTime.now(),departmentOne);
        Employee employee2 = new Employee("Alice","Ivanova",JobTitle.HR, "FEMALE",LocalDateTime.now(),departmentOne);
        employeeDAO.save(employee1);
        employeeDAO.save(employee2);
        List<Employee> employees = employeeDAO.findAll();
        employeeDAO.deleteById(employees.get(1).getId());
        employees = employeeDAO.findAll();
        assertEquals(1,employees.size());
    }

}