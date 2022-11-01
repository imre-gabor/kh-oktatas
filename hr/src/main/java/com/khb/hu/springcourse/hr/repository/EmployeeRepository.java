package com.khb.hu.springcourse.hr.repository;

import com.khb.hu.springcourse.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByJobStartingWithIgnoreCase(String jobName);
}
