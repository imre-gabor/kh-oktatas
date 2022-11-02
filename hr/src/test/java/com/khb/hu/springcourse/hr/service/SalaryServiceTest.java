package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SalaryServiceTest {

    @InjectMocks
    SalaryService salaryService;

    @Mock
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void raiseSalaryByJob() {

        //ARRANGE
        List<Employee> employees = Arrays.asList(
                new Employee(1, "name1", "job", 100000,
                        LocalDate.of(2021, 10, 1)),
                new Employee(2, "name2", "job2", 200000,
                        LocalDate.of(2021, 10, 1)));
        when(employeeRepository.findByJobStartingWithIgnoreCase("jo"))
                .thenReturn(employees);
        when(employeeService.getPayRaisePercent(any())).thenReturn(10);

        //ACT
        salaryService.raiseSalaryByJob("jo");

        //ASSERT
        assertThat(employees.get(0).getSalary()).isEqualTo(110000);
        assertThat(employees.get(1).getSalary()).isEqualTo(220000);
        verify(employeeService).getPayRaisePercent(employees.get(0));
        verify(employeeService).getPayRaisePercent(employees.get(1));
        verify(employeeRepository).findByJobStartingWithIgnoreCase("jo");
        verify(employeeRepository).save(employees.get(0));
        verify(employeeRepository).save(employees.get(1));
    }
}