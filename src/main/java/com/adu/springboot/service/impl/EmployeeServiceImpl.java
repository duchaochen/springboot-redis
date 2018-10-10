package com.adu.springboot.service.impl;

import com.adu.springboot.bean.Employee;
import com.adu.springboot.mapper.EmployeeMapper;
import com.adu.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getById(Integer id) {
        return employeeMapper.getById(id);
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
    }

    @Override
    public void deleteEmp(Integer id) {
        employeeMapper.deleteEmp(id);
    }

    @Override
    public void insertEmp(Employee employee) {
        employeeMapper.insertEmp(employee);
    }
}
