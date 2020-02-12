package com.zamoiski.service;

import com.zamoiski.EmployeeServiceImpl;
import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.model.Employee;
import com.zamoiski.error.NotFoundException;
import com.zamoiski.model.JobTitle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeServiceImpl service;

    @DisplayName("Test find all")
    @Test
    void findAll() {
        List<Employee> employees = new ArrayList<>();

        Employee employee = new Employee("Alice","Petrova", JobTitle.HR,
                "FEMALE", LocalDateTime.now(),null);

        employees.add(employee);

        when(employeeDAO.findAll()).thenReturn(employees);

        List<Employee> foundEmployees = service.findAll();

        verify(employeeDAO).findAll();

        assertThat(foundEmployees).hasSize(1);
    }

    @DisplayName("Test find by id")
    @Test
    void findById() {
        Employee employee = new Employee(1L,"Alice","Petrova", JobTitle.HR,
                "FEMALE", LocalDateTime.now(),null);

        when(employeeDAO.findById(anyLong())).thenReturn(employee);

        Employee foundEmployee = service.findById(1L);

        verify(employeeDAO).findById(anyLong());

        assertThat(foundEmployee).isNotNull();
    }

    @DisplayName("Test save")
    @Test
    void save() {
        Employee employee = new Employee();

        service.save(employee);

        verify(employeeDAO).save(any(Employee.class));
    }

    @Test
    void testNotFoundIdDelete(){
        assertThrows(NotFoundException.class,() -> service.deleteById(null));
    }

    @Test
    void testNotFoundId(){
        assertThrows(NotFoundException.class,() -> service.findById(null));
    }
}