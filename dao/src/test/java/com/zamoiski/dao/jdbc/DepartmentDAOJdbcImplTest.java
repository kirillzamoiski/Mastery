package com.zamoiski.dao.jdbc;

import com.zamoiski.config.TestConfiguration;
import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.model.Department;
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
class DepartmentDAOJdbcImplTest {

    @Autowired
    DepartmentDAO departmentDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("TRUNCATE TABLE department RESTART IDENTITY CASCADE;");
    }

    @Test
    void findAll() {
        Department department1 = new Department("RTG", LocalDateTime.now());
        Department department2 = new Department("RPG",LocalDateTime.now());
        departmentDAO.save(department1);
        departmentDAO.save(department2);
        List<Department> departments = departmentDAO.findAll();
        assertEquals(2,departments.size());
    }

    @Test
    void findById() {
        Department department1 = new Department("RTG", LocalDateTime.now());
        Department department2 = new Department("RPG",LocalDateTime.now());
        departmentDAO.save(department1);
        departmentDAO.save(department2);
        List<Department> departments = departmentDAO.findAll();
        Department department = departmentDAO.findById(departments.get(1).getId());
        assertEquals("RPG",department.getDepartment_name());
    }

    @Test
    void save() {
        Department department = new Department("RTG", LocalDateTime.now());
        departmentDAO.save(department);
        List<Department> departments = departmentDAO.findAll();
        assertEquals(1,departments.size());

    }

    @Test
    void deleteById() {
        Department department = new Department("DTP",LocalDateTime.now());
        departmentDAO.save(department);
        List<Department> departments = departmentDAO.findAll();
        assertEquals(1,departments.size());
        departmentDAO.deleteById(departments.get(0).getId());
        departments = departmentDAO.findAll();
        assertEquals(0,departments.size());
    }
}