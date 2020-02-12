package com.zamoiski.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zamoiski.DepartmentService;
import com.zamoiski.EmployeeRestController;
import com.zamoiski.EmployeeService;
import com.zamoiski.model.Department;
import com.zamoiski.model.Employee;
import com.zamoiski.model.JobTitle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRestControllerTest {

    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeRestController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1L,"Alice","Petrova", JobTitle.HR,
                "FEMALE", LocalDateTime.now(),null));

        employees.add(new Employee(3L,"Bob","Ivanov", JobTitle.TEAM_LEAD,
                "MALE", LocalDateTime.now(),null));

        when(service.findAll()).thenReturn(employees);

        mockMvc.perform(get("/api/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[1].id",is(3)));

        verify(service, times(1)).findAll();
    }

    @Test
    void findById() throws Exception {
        Employee employee = new Employee(1L,"Alice","Petrova", JobTitle.HR,
                "FEMALE", LocalDateTime.now(),null);


        when(service.findById(anyLong())).thenReturn(employee);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id",is(1)));

        verify(service, times(1)).findById(1L);
    }

    @Test
    void deleteEmployee() throws Exception {
        service.deleteById(anyLong());

        mockMvc.perform(
                delete("/api/employees/3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(2)).deleteById(anyLong());
    }
}