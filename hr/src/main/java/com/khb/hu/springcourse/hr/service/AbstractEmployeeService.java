package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public abstract class AbstractEmployeeService implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee modify(Employee employee) {
        if(employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }
}
