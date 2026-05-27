package com.example.demo.service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

      public Employee registerEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            // Preserve root cause so caller can log / display meaningful message
            throw new RuntimeException("Username already exists.", e);
        }
    }
    public Employee findByEmailid(String emailid) {
        return employeeRepository.findByEmailid(emailid);
    }
    public Employee saveEmployee(Employee employee) {
        throw new UnsupportedOperationException("Unimplemented method 'saveEmployee'");
    }
}
