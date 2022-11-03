package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Employee;

public interface EmployeeService {

    int getPayRaisePercent(Employee employee);

    Employee modify(Employee employee);
}
