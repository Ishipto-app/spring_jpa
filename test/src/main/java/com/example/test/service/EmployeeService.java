package com.example.test.service;

import com.example.test.entity.Employee;
import com.example.test.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@AllArgsConstructor
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    EntityManagerFactory emf;
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public Employee getEmployeeByLastName(String lastName){
        Query namedQuery = getEntityManager().createNamedQuery("Employee.findEmployeeByLastName");
        namedQuery.setParameter("lastName", lastName);
        return (Employee) namedQuery.getSingleResult();
    }
    public List<Employee> getEmployeePagination(int pageValue, int pageSize, String sort) {
        //page = 0 > lay trang dau tien
        //pageSize = 10 > 10 employee
        //sort = "laseName > sort by last name tang dan
        Pageable pageable = PageRequest.of(pageValue, pageSize, Sort.by(sort).ascending());
        Page<Employee> page = employeeRepository.findByOrderByLastNameAsc(pageable);
        return page.getContent();
    }
}
