package com.zamoiski.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name="employee")
@Getter @Setter @NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "First Name should not be empty")
    @NotEmpty(message = "First Name should not be empty")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name should not be empty")
    @NotEmpty(message = "Last Name should not be empty")
    private String lastName;

    @Column(name = "job_name")
    @NotNull(message = "JobTitle should not be empty")
    private JobTitle jobTitle;

    @Column(name = "gender")
    @NotNull(message = "gender should not be empty")
    @NotEmpty(message = "gender should not be empty")
    private String gender;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfBirth;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="department_id")
    private Department department;

    public Employee(Long id, String firstName, String lastName, JobTitle jobTitle, String gender, LocalDateTime dateOfBirth, Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
    }

    public Employee(String firstName, String lastName, JobTitle  jobTitle, String gender, LocalDateTime dateOfBirth, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
    }
}
