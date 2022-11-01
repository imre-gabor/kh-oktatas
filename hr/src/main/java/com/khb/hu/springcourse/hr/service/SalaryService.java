package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setNewSalary(Employee employee){
        int payRaisePercent = employeeService.getPayRaisePercent(employee);
        employee.setSalary(employee.getSalary() * (100 + payRaisePercent) / 100);
    }

    public void raiseSalaryByJob(String jobName) {
        employeeRepository.findByJobStartingWithIgnoreCase(jobName)
                .forEach(emp -> {
                    setNewSalary(emp);
                    employeeRepository.save(emp);   //ha @Transactional lenne a metódus, ez nem kellene
                });
    }

}
