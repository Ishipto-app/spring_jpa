package com.example.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQuery(
        name = "Employee.findEmployeeByLastName",
        query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"
)
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
