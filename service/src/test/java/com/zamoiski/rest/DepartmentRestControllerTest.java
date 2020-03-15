package com.zamoiski.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zamoiski.DepartmentRestController;
import com.zamoiski.DepartmentService;
import com.zamoiski.model.Department;
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
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DepartmentRestControllerTest {

    @Mock
    private DepartmentService service;

    @InjectMocks
    private DepartmentRestController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1L,"MMO", LocalDateTime.now()));
        departments.add(new Department(2L,"RPG", LocalDateTime.now()));

        when(service.findAll()).thenReturn(departments);

        mockMvc.perform(get("/api/departments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$",hasSize(2)));

        verify(service, times(1)).findAll();
    }

    @Test
    void findById() throws Exception {
        Department department = new Department(1L,"MMO",LocalDateTime.now());

        when(service.findById(anyLong())).thenReturn(department);

        mockMvc.perform(get("/api/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id",is(1)));

        verify(service, times(1)).findById(1L);
    }

    @Test
    void addEmployee() throws Exception {
        String departmnet = new ObjectMapper().writeValueAsString(new Department(1L,"MMO",null));

        service.save(any(Department.class));

        mockMvc.perform(
                post("/api/departments")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(departmnet)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        verify(service).save(any(Department.class));
    }

    @Test
    void updateEmployee() throws Exception {
        String employee = new ObjectMapper().writeValueAsString(new Department(1L,"MMO",null));

        service.save(any(Department.class));

        mockMvc.perform(
                put("/api/departments")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(employee)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        verify(service).save(any(Department.class));
    }

    @Test
    void deleteEmployee() throws Exception {
        service.deleteById(anyLong());

        mockMvc.perform(
                delete("/api/departments/3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(2)).deleteById(anyLong());
    }
}