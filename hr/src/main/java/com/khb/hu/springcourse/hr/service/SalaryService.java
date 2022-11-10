package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.jms.PayRaiseMessage;
import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void setNewSalary(Employee employee){
        int payRaisePercent = employeeService.getPayRaisePercent(employee);
        employee.setSalary(employee.getSalary() * (100 + payRaisePercent) / 100);
    }

    public void raiseSalaryByJob(String jobName) {
        employeeRepository.findByJobStartingWithIgnoreCase(jobName)
                .forEach(emp -> {
                    setNewSalary(emp);
                    employeeRepository.save(emp);   //ha @Transactional lenne a metódus, ez nem kellene
                    jmsTemplate.convertAndSend("payraise", new PayRaiseMessage(emp.getId(), emp.getSalary()));
                });
    }


    @Scheduled(cron = "${salaryreport.cron}")
    @SchedulerLock(name = "generateSalaryReport")
    public void generateSalaryReport(){
        employeeRepository.findAverageSalaries()
                .forEach(stat ->{
                    System.out.format("Average salary at company %d at job %s is %f%n", stat.getCompanyId(), stat.getJob(), stat.getAverageSalary());
                });
    }

}
