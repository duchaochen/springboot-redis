package com.adu.springboot.controller;

import com.adu.springboot.bean.Employee;
import com.adu.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getById(id);
        return employee;
    }

}
