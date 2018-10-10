package com.adu.springboot.service;

import com.adu.springboot.bean.Employee;

/**
 * @author Administrator
 */
public interface EmployeeService {

    Employee getById(Integer id);

    void updateEmp(Employee employee);

    void deleteEmp(Integer id);

    void insertEmp(Employee employee);
}
