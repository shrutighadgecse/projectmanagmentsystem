package com.example.demo.controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.demo.model.Employee;
import jakarta.servlet.http.HttpSession;



@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("currentUser")
    public Employee currentUser(HttpSession session) {
        return (Employee) session.getAttribute("user");
    }
}

    

