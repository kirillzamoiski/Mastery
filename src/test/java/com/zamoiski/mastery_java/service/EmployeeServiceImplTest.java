package com.zamoiski.mastery_java.service;

import com.zamoiski.mastery_java.dao.EmployeeDAO;
import com.zamoiski.mastery_java.entity.Employee;
import com.zamoiski.mastery_java.error.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        Employee employee = new Employee("Alice","Petrova",75,
                "PM","W",new GregorianCalendar(1989, Calendar.DECEMBER,12).getTime());

        employees.add(employee);

        when(employeeDAO.findAll()).thenReturn(employees);

        List<Employee> foundEmployees = service.findAll();

        verify(employeeDAO).findAll();

        assertThat(foundEmployees).hasSize(1);
    }

    @DisplayName("Test find by id")
    @Test
    void findById() {
        Employee employee = new Employee(1L,"Alice","Petrova",75,
                "PM","W",new GregorianCalendar(1989, Calendar.DECEMBER,12).getTime());

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
        assertThrows(NotFoundException.class,() ->{
            service.deleteById(null);
        });
    }

    @Test
    void testNotFoundId(){
        assertThrows(NotFoundException.class,() ->{
            service.findById(null);
        });
    }
}