package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.repository.CompanyRepository;
import com.khb.hu.springcourse.hr.repository.EmployeeRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
class CompanyServiceIT {

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void init(){
        employeeRepository.deleteAllInBatch();
        companyRepository.deleteAllInBatch();
    }


    @Test
    void findByExample_ById() {
        List<Company> companies = companyRepository.saveAll(Arrays.asList(
                new Company("company1"),
                new Company("company2"),
                new Company("comp3")));

        List<Company> found = companyService.findByExample(new Company(companies.get(1).getId()));

        assertThat(found).containsExactly(companies.get(1));
    }

    @Test
    void findByExample_ByName() {
        List<Company> companies = companyRepository.saveAll(Arrays.asList(
                new Company("company1"),
                new Company("company2"),
                new Company("comp3")));

        List<Company> found = companyService.findByExample(new Company("ny"));

        assertThat(found).containsExactly(companies.get(0), companies.get(1));
    }

    @Test
    void findByExample_ByIdAndName() {
        List<Company> companies = companyRepository.saveAll(Arrays.asList(
                new Company("company1"),
                new Company("company2"),
                new Company("comp3")));

        List<Company> found = companyService.findByExample(new Company(companies.get(1).getId(),"ny"));

        assertThat(found).containsExactly(companies.get(1));
    }
}