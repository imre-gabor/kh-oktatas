package com.khb.hu.springcourse.hr.repository;

import com.khb.hu.springcourse.hr.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
