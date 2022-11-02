package com.khb.hu.springcourse.hr.repository;

import com.khb.hu.springcourse.hr.model.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer>, JpaSpecificationExecutor<Company> {

    @Query("SELECT c FROM Company c WHERE c.id=:id")
    @EntityGraph(attributePaths = "employees")
    Optional<Company> findByIdWithEmployees(Integer id);
}
