package com.example.test.repository;

import com.example.test.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);

    List<Employee> findByFirstNameOrLastName(String firstName, String lastName);

    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

    List<Employee> findByFirstNameIgnoreCase(String firstName);

    @Query("SELECT e FROM Employee e WHERE e.emailAddress = :emailAddress AND e.lastName = :lastName")
    List<Employee> findByEmailAndLastName(String emailAddress, String lastName);

    Page<Employee> findByOrderByLastNameAsc(Pageable pageable);
}