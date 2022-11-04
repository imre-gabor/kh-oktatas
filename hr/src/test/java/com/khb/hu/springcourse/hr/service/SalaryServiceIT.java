package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import static org.assertj.core.api.Assertions.*;

import com.khb.hu.springcourse.hr.util.IntegrationTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class SalaryServiceIT extends IntegrationTestBase {

    @Autowired
    SalaryService salaryService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void raiseSalaryByJob() {
        //ARRANGE
        List<Employee> employees = employeeRepository.saveAll(
                Arrays.asList(
                new Employee(null, "name1", "job", 100000,
                        LocalDate.of(2000, 10, 1)),
                new Employee(null, "name2", "JoB2", 200000,
                        LocalDate.of(2021, 10, 1)),
                new Employee(null, "name3", "otherjob", 200000,
                        LocalDate.of(2021, 10, 1))));
        //ACT
        salaryService.raiseSalaryByJob("jo");

        //ASSERT
        List<Employee> employeesAfterPayRaise = employeeRepository.findAll(Sort.by("id"));
        assertThat(employeesAfterPayRaise.stream().map(Employee::getSalary).toList())
                .containsExactly(110000.0, 220000.0, 200000.0);
    }

    @Test
    void raiseSalaryByJob2() {
        //ARRANGE
        List<Employee> employees = employeeRepository.saveAll(
                Arrays.asList(
                        new Employee(null, "name1", "job", 100000,
                                LocalDate.of(2000, 10, 1)),
                        new Employee(null, "name2", "JoB2", 200000,
                                LocalDate.of(2021, 10, 1)),
                        new Employee(null, "name3", "otherjob", 200000,
                                LocalDate.of(2021, 10, 1))));
        //ACT
        salaryService.raiseSalaryByJob("jo");

        //ASSERT
        List<Employee> employeesAfterPayRaise = employeeRepository.findAll(Sort.by("id"));
        assertThat(employeesAfterPayRaise.stream().map(Employee::getSalary).toList())
                .containsExactly(110000.0, 220000.0, 200000.0);
    }
}