package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "register")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length=30)
    private String employeename;

    @Column(nullable = false, unique = true, length = 65)
    private String emailid;

    @Column(nullable = false, length=30)
    private String employeecontact;

    @Column(nullable = false, length=30)
    private String department;

    @Column(nullable = false, length=65)
    private String createpassword;

    @Column(nullable = false, length=65)
    private String confirmpassword;

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmployeename() {
        return employeename;
    }
    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }
    public String getEmailid() {
        return emailid;
    }
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
    public String getEmployeecontact() {
        return employeecontact;
    }
    public void setEmployeecontact(String employeecontact) {
        this.employeecontact = employeecontact;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getCreatepassword() {
        return createpassword;
    }
    public void setCreatepassword(String createpassword) {
        this.createpassword = createpassword;
    }
    public String getConfirmpassword() {
        return confirmpassword;
    }
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    public Object getReportingBoss() {
       
        throw new UnsupportedOperationException("Unimplemented method 'getReportingBoss'");
    }
    public void setTimestamp(LocalDateTime now) {
        throw new UnsupportedOperationException("Unimplemented method 'setTimestamp'");
    }
    public Object getTimestamp() {
        throw new UnsupportedOperationException("Unimplemented method 'getTimestamp'");
    }

    // Getters and Setters
}
