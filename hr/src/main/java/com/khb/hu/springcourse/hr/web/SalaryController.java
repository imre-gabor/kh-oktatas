package com.khb.hu.springcourse.hr.web;

import com.khb.hu.springcourse.hr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @PutMapping("/api/salary/raise")
    public void raiseSalaryByJob(@RequestParam String job){
        salaryService.raiseSalaryByJob(job);
    }
}
