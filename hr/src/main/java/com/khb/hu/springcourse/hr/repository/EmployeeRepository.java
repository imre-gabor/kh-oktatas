package com.khb.hu.springcourse.hr.repository;

import com.khb.hu.springcourse.hr.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByJobStartingWithIgnoreCase(String jobName);

    @Query("SELECT e FROM Employee e WHERE e.id=:id")
    @EntityGraph(attributePaths = "company.addresses")
    Optional<Employee> findByIdWithCompanyAndAddresses(int id);
}
