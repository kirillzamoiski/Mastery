package com.zamoiski.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="department")
@Getter @Setter @NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "department_name")
    @NotNull(message = "First Name should not be empty")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String department_name;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @Column(name = "date_of_create")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfCreate;


    public Department(Long id,String department_name, LocalDateTime dateOfCreate) {
        this.id = id;
        this.department_name = department_name;
        this.dateOfCreate = dateOfCreate;
    }

    public Department(String department_name, LocalDateTime dateOfCreate) {
        this.department_name = department_name;
        this.dateOfCreate = dateOfCreate;
    }

    public Department(Long id){
        this.id = id;
    }
}
