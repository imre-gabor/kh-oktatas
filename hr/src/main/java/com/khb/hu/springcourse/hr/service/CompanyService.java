package com.khb.hu.springcourse.hr.service;

import com.khb.hu.springcourse.hr.model.Company;
import com.khb.hu.springcourse.hr.model.Employee;
import com.khb.hu.springcourse.hr.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import static com.khb.hu.springcourse.hr.service.CompanySpecifications.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findByExample(Company company) {
        return companyRepository.findAll(
                Example.of(company,
                        ExampleMatcher.matching()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)));
    }

    public List<Company> findByExampleWithSpecification(Company company) {
        Integer id = company.getId();
        String name = company.getName();

        Specification<Company> spec = Specification.where(null);
        if(id != null){
            spec = spec.and(idEquals(id));
        }

        if(StringUtils.hasLength(name)){
            spec = spec.and(nameContains(name));
        }

        if(!CollectionUtils.isEmpty(company.getEmployees())) {
            Employee employee = company.getEmployees().get(0);
            String employeeName = employee.getName();
            LocalDate employeeWorkStart = employee.getWorkStart();
            if(StringUtils.hasLength(employeeName)){
                spec = spec.and(hasEmployeeWithNamePrefix(employeeName));
            }
            if(employeeWorkStart != null){
                spec = spec.and(hasEmployeeStartedWorkingAtMonthOf(employeeWorkStart));
            }
        }

        return companyRepository.findAll(spec);
    }


}