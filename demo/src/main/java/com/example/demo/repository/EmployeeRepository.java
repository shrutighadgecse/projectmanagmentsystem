package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("SELECT e FROM Employee e WHERE LOWER(e.emailid) = LOWER(:emailid)")
    Employee findByEmailid(@Param("emailid") String emailid);

    @SuppressWarnings({ "unchecked" })
    Employee save(Employee employee);
}

